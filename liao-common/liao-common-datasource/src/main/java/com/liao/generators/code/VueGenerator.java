package com.liao.generators.code;

import com.liao.common.utils.StringUtils;
import com.liao.generators.config.GenConfig;
import com.liao.generators.entity.GenTableColumns;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * <p>
 * Vue 生成
 * </p>
 *
 * @author LiAo
 * @since 2021/3/16
 */
public class VueGenerator {
    // 数据库名称
    public static String database;
    // 主机地址
    public static String host;
    // 端口号
    public static String port;
    // 数据库用户名
    public static String username;
    // 用户密码
    public static String password;
    // 表名
    public static String[] tableNmae;

    public VueGenerator(String database, String host, String port, String username, String password, String[] tableNmae) {
        VueGenerator.database = database;
        VueGenerator.host = host;
        VueGenerator.port = port;
        VueGenerator.username = username;
        VueGenerator.password = password;
        VueGenerator.tableNmae = tableNmae;
    }

    //获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            String user = username;
            String pass = password;
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 获取当前项目路径
    public static String projectPath = System.getProperty("user.dir") + "/vue";

    /**
     * 获取某表数据及信息
     */
    public void VueTest(String[] tables) {

        /*String[] tables = new String[]{"sys_real_name"};*/

        System.out.println("----------------------开始代码生成------------------------");

        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /**/
        String[] templates = new String[]{"templates/api.js.vm", "templates/index.vue.vm"};


        List<GenConfig> genConfig = getGenConfig(tables);

        // 变量
        VelocityContext velocityContext = new VelocityContext();

        for (GenConfig config : genConfig) {
            velocityContext.put("packageName", config.getPackageName());
            velocityContext.put("tableName", config.getTableName());
            velocityContext.put("tableComment", config.getColumns());
            velocityContext.put("className", config.getClassName());
            velocityContext.put("finleName", config.getFinleName());
            velocityContext.put("genPath", config.getGenPath());
            velocityContext.put("columns", config.getColumns());

            for (String template : templates) {
                // 模板列表
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(velocityContext, sw);

                String path = config.getGenPath() + "/" + config.getFinleName() + getFileName(config.packageName, template);
                try {
                    /*System.out.println(path);*/
                    FileUtils.writeStringToFile(new File(path), sw.toString(), "UTF-8");
                    /*System.out.println(sw.toString());*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("----------------------END------------------------");
    }

    public static List<GenConfig> getGenConfig(String[] tables) {
        List<GenConfig> genConfigs = new ArrayList<>();
        for (String table : tables) {
            GenConfig genConfig = new GenConfig();
            getTableData(genConfig, database, table);
            getGenConfig(genConfig.getColumns(), database, table);
            genConfigs.add(genConfig);
        }

        genConfigs.forEach(System.out::println);

        return genConfigs;
    }


    /**
     * 获取表列信息
     *
     * @param columns      表信息
     * @param dataBaseName 库名
     * @param tables       表名称
     */
    public static void getGenConfig(List<GenTableColumns> columns, String dataBaseName, String tables) {
        Connection conn = getConnection();
        String sql = "select  *  from " + tables;
        PreparedStatement stmt;
        ResultSet resultSet = null;

        try {

            stmt = conn.prepareStatement(sql);
            resultSet = stmt.executeQuery(sql);
            ResultSetMetaData data = resultSet.getMetaData();

            for (int i = 1; i <= data.getColumnCount(); i++) {
                GenTableColumns genTableColumn = new GenTableColumns();
                //  获得指定列的列名
                String columnName = data.getColumnName(i);
                // 列名称转驼峰
                genTableColumn.setJavaField(StringUtils.toCamelCase(columnName));

                columns.add(genTableColumn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getGenConfig(columns, tables);
    }

    public static void getGenConfig(List<GenTableColumns> columns, String tables) {
        Connection conn = getConnection();
        String sql = "show full columns from " + tables;
        PreparedStatement stmt;
        ResultSet resultSet = null;

        try {

            stmt = conn.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            ResultSetMetaData data = resultSet.getMetaData();

            for (GenTableColumns column : columns) {
                if (resultSet.next()) {
                    column.setColumnComment(resultSet.getString("Comment"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取表信息
     *
     * @param genConfig    表信息
     * @param dataBaseName 库名
     * @param table        表名称
     */
    public static void getTableData(GenConfig genConfig, String dataBaseName, String table) {
        try {
            Connection conn = getConnection();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            // 表名称
            genConfig.setTableName(table);

            String s = table.split("_")[table.split("_").length - 1];
            // 包路径
            genConfig.setPackageName("/" + s);

            // 文件名
            genConfig.setFinleName(s);
            ResultSet rs = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                if (rs.getString("TABLE_CAT").equals(dataBaseName) && rs.getString("TABLE_NAME").equals(table)) {
                    // 表备注
                    genConfig.setTableComment(rs.getString("REMARKS"));
                    // 表名称
                    String table_name = rs.getString("TABLE_NAME");
                    genConfig.setTableName(table_name.replace('_', '-'));
                }
            }
            // 路径
            genConfig.setGenPath(projectPath);
            // 实体类名称
            genConfig.setClassName(s.substring(0, 1).toUpperCase() + s.substring(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取代码生文件名
     *
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getFileName(String packgeName, String template) {
        if (template.equals("templates/api.js.vm")) {
            return packgeName + ".js";
        } else if (template.equals("templates/index.vue.vm")) {
            return "/index.vue";
        }
        return null;
    }


}
