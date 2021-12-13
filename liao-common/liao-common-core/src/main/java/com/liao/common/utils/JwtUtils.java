package com.liao.common.utils;

import com.liao.common.constant.TokenConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

/**
 * <p>
 *  Jwt工具类
 * </p>
 *
 * @author LiAo
 * @since 2021/12/13
 */
public class JwtUtils {

    public static String secret = TokenConstants.SECRET;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims){
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
