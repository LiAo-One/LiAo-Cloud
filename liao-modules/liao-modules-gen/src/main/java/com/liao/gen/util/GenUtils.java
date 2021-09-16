package com.liao.gen.util;

import com.liao.common.constant.GenConstants;
import com.liao.common.utils.StringUtils;
import com.liao.gen.config.GenConfig;
import com.liao.gen.entity.GenTable;
import com.liao.gen.entity.GenTableColumn;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

/**
 * <p>
 * 代码生成器 工具类
 * </p>
 *
 * @author LiAo
 * @since 2021/7/7
 */
public class GenUtils {


    /**
     * 格式化表数据
     *
     * @param genTable 表对象
     */
    public static void initTable(GenTable genTable) {
        // 表名转驼峰
        genTable.setClassName(convertClassName(genTable.getTableName()));
        // 设置包名
        genTable.setPackageName(GenConfig.getPackageName());
        // 设置模块名
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        // 设置业务名
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        // 设置功能名
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        // 作者
        genTable.setFunctionAuthor(GenConfig.getAuthor());
    }

    /**
     * 初始化列表属性字段
     *
     * @param column 表属性
     * @param table  表
     */
    public static void initColumnField(GenTableColumn column, GenTable table) {
        // 获取列类型
        String dataType = getDbType(column.getColumnType());
        // 获取列名称
        String columnName = column.getColumnName();
        // 构建父子表关系
        column.setTableId(table.getTableId());
        // 数据库字段转驼峰
        column.setJavaField(StringUtils.toCamelCase(columnName));
        // 设置数据类型
        column.setJavaType(GenConstants.TYPE_STRING);

        // 判断是否为数据库文本类型和数据库字符串类型
        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
            // 获取类型长度 超过500设为文本域
            Integer columnLength = getColumnLength(column.getColumnType());

            // 超过500就是文本域 小于 就是 输入框
            String htmlType = columnLength >= 500 || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;

            // 设置组件类型
            column.setHtmlType(htmlType);
        } else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) { // 时间类型判断
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) { // 数字类型
            // 文本框
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 浮点类型 BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");

            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }

        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.isPk()) {
            column.setIsEdit(GenConstants.REQUIRE);
        }

        // 列表字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.isPk()) {
            column.setIsList(GenConstants.REQUIRE);
        }

        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk()) {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }

        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type") ||
                StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
        }

        // 文件字段设置文件上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
        }

        // 内容字段设置富文本控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    /**
     * 表名称转化为Java 类名
     *
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName) {

        // 是否去除表前缀
        boolean autoRemovePre = GenConfig.getAutoRemovePre();

        // 需要去除的表前缀集合
        String tablePrefix = GenConfig.getTablePrefix();

        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
            // 去除前缀
            String[] searchList = tablePrefix.split(",");
            tableName = replaceFirst(tableName, searchList);
        }

        // 转驼峰
        return StringUtils.convertToCamelCase(tableName);
    }


    /**
     * 批量替换前缀
     *
     * @param replacementm 替换值
     * @param searchList   替换列表
     * @return 结果
     */
    public static String replaceFirst(String replacementm, String[] searchList) {
        String text = replacementm;

        for (String searchString : searchList) {
            // 判断是否以指定前缀开始
            if (replacementm.startsWith(searchString)) {
                text = replacementm.replaceFirst(searchString, "");
            }
        }
        return text;
    }


    /**
     * 获取模块名
     *
     * @param packageName 配置中的包路径
     * @return 结果
     */
    public static String getModuleName(String packageName) {

        // 返回 . 最后一次在包路径中的索引
        int lastIndex = packageName.lastIndexOf(".");
        // 获取包路径字符串长度
        int nameLength = packageName.length();

        // 返回截取后的包名
        return StringUtils.substring(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 获取业务名
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        // 获取 _ 最后一次在表名称中的索引
        int lastIndex = tableName.lastIndexOf("_");
        // 表名称字符长度
        int nameLength = tableName.length();
        // 截取的业务名
        return StringUtils.substring(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 表描述
     *
     * @param text 需要被替换的文字
     * @return 结果
     */
    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text, "(?:表|LiAo)", "");
    }

    /**
     * 获取数据类型
     *
     * @param columnType 数据库类型
     * @return 截取
     */
    public static String getDbType(String columnType) {

        if (StringUtils.indexOf(columnType, "(") > 0) {
            return StringUtils.substringBefore(columnType, "(");
        } else {
            return columnType;
        }
    }


    /**
     * 校验数据是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 被校验的值
     * @return true:包含 ？ false 不包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }


    /**
     * 获取字段长度
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }
}
