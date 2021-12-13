package com.liao.common.constant;

import com.liao.common.utils.StringUtils;

/**
 * <p>
 * 权限相关通用常量
 * </p>
 *
 * @author LiAo
 * @since 2021/9/15
 */
public class SecurityConstants {

    // 令牌自定义标识
    public static final String TOKEN_AUTHENTICATION = "Authorization";

    // 令牌前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 合法请求标志
    public static final String X_SIGN = "X-Sign";

    // 请求时间戳
    public static final String Time_Info = "Time-Info";

    // 请求来源
    public static final String FROM_SOURCE = "from-source";

    // 内部请求
    public static final String INNER = "inner";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "login_user";

    /**
     * 用户标识
     */
    public static final String USER_KEY = "user_key";

    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

}
