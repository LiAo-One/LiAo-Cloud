package com.liao.common.constant;

/**
 * <p>
 * 代码生成常量
 * </p>
 *
 * @author LiAo
 * @since 2021/7/8
 */
public class GenConstants {

    // 数据库数字类型
    public static final String[] COLUMNTYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bit", "bigint", "float", "double", "decimal"};

    // 数据库字符串类型
    public static final String[] COLUMNTYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    // 数据库文本类型
    public static final String[] COLUMNTYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    // 数据库时间类型
    public static final String[] COLUMNTYPE_TIME = {"datetime", "time", "date", "timestamp"};

    // 文本框
    public static final String HTML_INPUT = "input";

    // 下拉框
    public static final String HTML_SELECT = "select";

    // 单选框
    public static final String HTML_RADIO = "radio";

    // 图片上传控件
    public static final String HTML_IMAGE_UPLOAD = "imageUpload";

    // 文件上传控件
    public static final String HTML_FILE_UPLOAD = "fileUpload";

    // 富文本控件
    public static final String HTML_EDITOR = "editor";

    // 文本域
    public static final String HTML_TEXTAREA = "textarea";

    // 日期控件
    public static final String HTML_DATETIME = "datetime";

    // 字符串类型
    public static final String TYPE_STRING = "String";

    // 整型
    public static final String TYPE_INTEGER = "Integer";

    // 长整型
    public static final String TYPE_LONG = "Long";

    // 浮点型
    public static final String TYPE_DOUBLE = "Double";

    // 高精度计算类型
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    // 时间类型
    public static final String TYPE_DATE = "Date";

    // 页面不需要编辑的字段
    public static final String[] COLUMNNAME_NOT_EDIT = {"id", "create_by", "deleted", "version", "update_time", "create_time", "del_flag"};

    // 页面不需要显示的列表字段
    public static final String[] COLUMNNAME_NOT_LIST = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time", "deleted", "version"};

    // 页面不需要查询字段
    public static final String[] COLUMNNAME_NOT_QUERY = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time", "remark", "deleted", "version"};

    // 模糊查询
    public static final String QUERY_LIKE = "LIKE";

    // 需要
    public static final String REQUIRE = "1";
}
