package com.liao.common.service;

/*import cn.hutool.core.util.IdUtil;
import com.liao.cache.service.RedisService;
import com.liao.common.constant.CacheConstants;
import com.liao.common.constant.SecurityConstants;
import com.liao.common.core.R;
import com.liao.common.utils.JwtUtils;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.ip.IpUtils;
import com.liao.system.api.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;*/

/**
 * <p>
 * token 验证及处理
 * </p>
 *
 * @author LiAo
 * @since 2021/12/13
 */
public class TokenService {

    /*protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;


    @Autowired
    private RedisService redisService;*/

    /**
     * 创建令牌
     *
     * @param loginUser 登录信息
     * @return 结果
     */
    /*public R createToken(LoginUser loginUser) {
        String token = IdUtil.fastUUID();
        Long adminId = loginUser.getSysAdmin().getAdminId();
        String adminName = loginUser.getSysAdmin().getAdminName();

        loginUser.setToken(token);
        loginUser.setUserid(adminId);
        loginUser.setUsername(adminName);
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));

        refreshToken(loginUser);


        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, adminId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, adminName);

        R success = R.success();
        success.put("access_token", JwtUtils.createToken(claimsMap));
        success.put("expires_in", expireTime);

        return success;
    }*/

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    /*public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }*/
}
