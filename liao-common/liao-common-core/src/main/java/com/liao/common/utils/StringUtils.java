package com.liao.common.utils;

import cn.hutool.core.text.StrFormatter;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 转驼峰
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");


    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str);
    }

    /**
     * 判断字符串是否为非空
     *
     * @param str String
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断一个数组是否为空
     *
     * @param obj 要判断的数组
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] obj) {
        return isNull(obj);
    }

    /**
     * * 判断一个数组是否为非空
     *
     * @param obj 要判断的数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] obj) {
        return !isEmpty(obj);
    }


    /**
     * 判断一个Number是否为空
     *
     * @param number 要判断的Number
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Number number) {
        return isNull(number);
    }

    /**
     * 判断一个Number是否为空
     *
     * @param number 要判断的Number
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Number number) {
        return !isEmpty(number);
    }

    /**
     * Integer 是否 大于0
     *
     * @param integer 数据
     * @return true 大于0  false：小于等于0
     */
    public static boolean isNotZero(Integer integer) {
        return integer != null && integer > 0;
    }

    /**
     * Integer 是否不为0
     *
     * @param integer 数据
     * @return true 小于0  false：大于0
     */
    public static boolean isZero(Integer integer) {
        return !isNotZero(integer);
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 驼峰转下划线
     *
     * @param str String
     * @return data
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) return null;

        StringBuilder sb = new StringBuilder();
        // 前置字符串是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (i > 0) {
                // 检测前置字符串是否大写
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            // 当前字符串是否大写
            curreCharIsUpperCase = Character.isUpperCase(c);

            // 下一字符串是否大写
            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }



    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params 参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params)
    {
        if (isEmpty(params) || isEmpty(template))
        {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str 指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strs)
    {
        if (isEmpty(str) || isEmpty(strs))
        {
            return false;
        }
        for (String pattern : strs)
        {
            if (isMatch(pattern, str))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url 需要匹配的url
     * @return
     */
    public static boolean isMatch(String pattern, String url)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {

        // 为null 返回 ""
        if (str == null) {
            return NULLSTR;
        }

        // 结束长度小于0
        if (end < 0) {
            end = str.length() + end;
        }

        // 开始长度小于0
        if (start < 0) {
            start = str.length() + start;
        }

        // 结束长度大于自身长度
        if (end > str.length()) {
            end = str.length();
        }

        // 开始长度大于结束长度
        if (start > end) {
            return NULLSTR;
        }

        // 开始长度小于0
        if (start < 0) {
            start = 0;
        }

        // 结束长度小于0
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty())
        {
            // 没必要转换
            return "";
        }
        else if (!name.contains("_"))
        {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty())
            {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }


    /**
     * 下划线转驼峰
     *
     * @param s 文本
     * @return 驼峰
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

}
