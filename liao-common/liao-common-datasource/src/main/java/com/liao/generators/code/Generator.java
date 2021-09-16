package com.liao.generators.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @author LiAo
 * @since 2021/1/12
 */
public class Generator {
    // 数据库名称
    public static String database = "liao_module";
    // 主机地址
    public static String host = "localhost";
    // 端口号
    public static String port = "3306";
    // 数据库用户名
    public static String username = "root";
    // 用户密码
    public static String password = "liao";
    // 表名
    public static String[] tableNmae = new String[]{"gen_table", "gen_table_column"};

    public static void main(String[] args) {
        // Vue 代码生成器
        VueGenerator vueGenerator = new VueGenerator(database, host, port, username, password, tableNmae);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取当前项目路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/mybatis-plus/src/main/java");
        // 作者名字
        gc.setAuthor("LiAo");
        // 是否打开资源管理器
        gc.setOpen(false);
        // 是否覆盖原来生成的
        gc.setFileOverride(false);
        // 去除Service I 前缀
        gc.setServiceName("%sService");
        // 主键生成策略
        gc.setIdType(IdType.ID_WORKER);
        // set 日期的类型
        gc.setDateType(DateType.ONLY_DATE);
        // Seagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName("blog");
        pc.setParent("com.ly.output");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("services");
        pc.setServiceImpl("services.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("/templates/entity.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setController("/templates/controller.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表明下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 列名字下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //跳过视图
        strategy.setSkipView(true);

        // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        // 自动Lombok
        strategy.setEntityLombokModel(true);

        // Controller Rest 风格
        strategy.setRestControllerStyle(true);

        // 逻辑删除字段
        strategy.setLogicDeleteFieldName("deleted");

        // 乐观锁
        strategy.setVersionFieldName("version");

        // 自动生成策略
        TableFill gmtCreate = new TableFill("createTime", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("updateTime", FieldFill.INSERT_UPDATE);

        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tableNmae);
        strategy.setSuperEntityColumns("id");
        // url 使用下划线命名
        strategy.setControllerMappingHyphenStyle(true);

        // strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        vueGenerator.VueTest(tableNmae);
    }
}
