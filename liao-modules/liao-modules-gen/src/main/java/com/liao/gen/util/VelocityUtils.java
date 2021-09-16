package com.liao.gen.util;

import com.liao.common.constant.GenConstants;
import com.liao.common.utils.DateUtils;
import com.liao.common.utils.StringUtils;
import com.liao.gen.entity.GenTable;
import com.liao.gen.entity.GenTableColumn;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * Velocity 模板工具类
 * </p>
 *
 * @author LiAo
 * @since 2021/7/9
 */
public class VelocityUtils {

    // 项目空间路径
    private static final String PROJECT_PATH = "main/java";

    // mybatis空间路径
    private static final String MYBATIS_PATH = "main/resources/mapper";

    // 默认上级菜单，系统工具
    private static final String DEFAULT_PARENT_MENU_ID = "3";


    /**
     * 设置模板变量信息
     *
     * @param genTable 表参数
     * @return 结果
     */
    public static VelocityContext prepareContext(GenTable genTable) {

        // 生成模块名
        String moduleName = genTable.getModuleName();
        // 生成业务名
        String businessName = genTable.getBusinessName();
        // 生成包路径
        String packageName = genTable.getPackageName();
        // 生成功能名
        String functionName = genTable.getFunctionName();

        // 模板存储键值对
        VelocityContext velocityContext = new VelocityContext();
        // 表名称
        velocityContext.put("tableName", genTable.getTableName());
        // 功能名
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        // 实体类名称
        velocityContext.put("ClassName", genTable.getClassName());
        // 小写类名
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        // 模块名
        velocityContext.put("moduleName", genTable.getModuleName());
        // 业务名 首字母大写
        velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        // 业务名 首字母小写
        velocityContext.put("businessName", genTable.getBusinessName());
        // 包前缀
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        // 包路径
        velocityContext.put("packageName", packageName);
        // 作者
        velocityContext.put("author", genTable.getFunctionAuthor());
        // 当前日期
        velocityContext.put("datetime", DateUtils.getDate());
        // 主键信息
        velocityContext.put("pkColumn", genTable.getPkColumn());
        // java 业务代码需要的包路径
        velocityContext.put("importList", getImportList(genTable));
        // 字段信息
        velocityContext.put("columns", genTable.getColumns());
        // 业务参数
        velocityContext.put("table", genTable);
        // 父菜单id
        velocityContext.put("parentMenuId", genTable.getParentMenuId());
        // 返回模板
        return velocityContext;
    }


    /**
     * 加载模板列表
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/vm/java/entity.java.vm");
        templates.add("templates/vm/java/dao.java.vm");
        templates.add("templates/vm/java/service.java.vm");
        templates.add("templates/vm/java/serviceImpl.java.vm");
        templates.add("templates/vm/java/controller.java.vm");
        templates.add("templates/vm/xml/mapper.xml.vm");
        templates.add("templates/vm/sql/sql.vm");
        templates.add("templates/vm/js/api.js.vm");
        templates.add("templates/vm/vue/index.vue.vm");

        return templates;
    }


    /**
     * 获取文件名
     *
     * @param template 模板名称
     * @param genTable 业务信息
     * @return 文件名
     */
    public static String getFileName(String template, GenTable genTable) {

        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();
        // Java 包路径
        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        // 拼接模块路径 完整
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        // vue 路径
        String vuePath = "vue";

        // 实体类路径
        if (template.contains("entity.java.vm")) {
            fileName = StringUtils.format("{}/entity/{}.java", javaPath, className);
        }

        // Dao 路径
        else if (template.contains("dao.java.vm")) {
            fileName = StringUtils.format("{}/dao/{}Mapper.java", javaPath, className);
        }
        // Services 路径
        else if (template.contains("service.java.vm")) {
            fileName = StringUtils.format("{}/service/{}Service.java", javaPath, className);
        }
        // Services 实现类路径
        else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        }

        // Controller 路径
        else if (template.contains("controller.java.vm")) {
            fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
        }

        // mybatis xml
        else if (template.contains("mapper.xml.vm")) {
            fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        }

        // 按钮SQL语句
        else if (template.contains("sql.vm")) {
            fileName = businessName + "Menu.sql";
        }

        // Axios 请求方法
        else if (template.contains("api.js.vm")) {
            fileName = StringUtils.format("{}/api/{}/{}.js", vuePath, moduleName, businessName);
        }

        // Vue index 页面
        else if (template.contains("index.vue.vm")) {
            fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
        }

        return fileName;
    }


    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        // 获取最后一次出现的索引
        int lastIndex = packageName.lastIndexOf(".");
        // 截取包名
        return StringUtils.substring(packageName, 0, lastIndex);
    }


    // 根据列类型导入包

    /**
     * 根据数据类型 导入Java包
     *
     * @param genTable
     * @return
     */
    public static HashSet<String> getImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();


        // 包路径信息
        HashSet<String> importList = new HashSet<>();

        // list 路径 用于批量操作接口
        importList.add("java.util.List");

        for (GenTableColumn column : columns) {
            // 时间类型
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            // 高精度类型
            else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }

        return importList;
    }

}
