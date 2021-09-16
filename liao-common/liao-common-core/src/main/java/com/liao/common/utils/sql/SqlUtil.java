package com.liao.common.utils.sql;

import com.liao.common.utils.StringUtils;

/**
 * <p>
 * SQL 操作类
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class SqlUtil {

    // 降序
    public static final String DESC = "desc";

    // 升序
    public static final String ASC = "asc";

    // 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
    public static final String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符串 防止SQL注入
     *
     * @param value 语句
     * @return 校验后的语句
     */
    public static String escapeOrderBySql(String value) {

        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }

        return value;
    }

    /**
     * 验证order by 语法是否符合规范
     *
     * @param value 语句
     * @return true:符合 false 不符合
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }

    /**
     * 判断排序方式
     *
     * @param value 排序标签
     * @return true:升序 false:降序
     */
    public static boolean getOrderBy(String value) {
        return value.equals(ASC);
    }
}
