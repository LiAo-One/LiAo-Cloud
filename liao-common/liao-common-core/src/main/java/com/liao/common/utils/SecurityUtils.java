package com.liao.common.utils;

import com.liao.common.constant.SecurityConstants;

/**
 * <p>
 * 权限安全工具类
 * </p>
 *
 * @author LiAo
 * @since 2021/9/15
 */
public class SecurityUtils {

    /**
     * 替换Token 前缀
     *
     * @param token token
     * @return 结果
     */
    public static String replaceTokenPrefix(String token) {
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }

        return token;
    }
}
