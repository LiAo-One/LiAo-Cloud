package com.liao.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liao.common.constant.SecurityConstants;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Token工具类
 * </p>
 *
 * @author LiAo
 * @since 2020/12/18
 */
public class TokenUtil {

    //设置过期时间 7天
    private static final long EXPIRE_DATE = 60 * 60 * 1000 * 24 * 7;
    //token秘钥
    private static final String TOKEN_SECRET = "5fdc3f475e8f4c0a2641be47";


    /**
     * 实现签名方法
     *
     * @param json 生成的json
     * @return token
     */
    public static String token(Map<String,String> json) {

        String token = "";

        Set<String> keys = json.keySet();
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            /*token = JWT.create()
                    .withHeader(header)
                    .withClaim("data", json)
                    .withClaim("data", json).withExpiresAt(date)
                    .sign(algorithm);*/

            // 设置头部信息
            JWTCreator.Builder builder = JWT.create().withHeader(header);

            // 循环生成Token
            for (String key : keys) {
                builder.withClaim(key,json.get(key));
            }

            token = builder.withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * 校验token
     *
     * @param token token
     * @return true：有效？false失效
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解析Token中的信息
     *
     * @param head  头部
     * @param token token
     * @return json格式数据
     */
    public static String getTokenMes(String head, String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(head).asString();

        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Token
     *
     * @param request 请求
     * @return token
     */
    public static String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(SecurityConstants.TOKEN_AUTHENTICATION);

        return SecurityUtils.replaceTokenPrefix(token);
    }


    /**
     * 获取Token
     *
     * @param request 请求  HttpServletRequest
     * @return token
     */
    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.TOKEN_AUTHENTICATION);
        return SecurityUtils.replaceTokenPrefix(token);
    }

    /**
     * 获取X_Sign
     *
     * @param request 请求
     * @return token
     */
    public static String getXSign(ServerHttpRequest request) {
        return request.getHeaders().getFirst(SecurityConstants.X_SIGN);
    }

    /**
     * 获取Token 请求
     *
     * @return token
     */
    public static String getToken() {

        // 获取ServerHttpRequest 参数
        String token = ServletUtils.getRequest().getHeader(SecurityConstants.TOKEN_AUTHENTICATION);

        return SecurityUtils.replaceTokenPrefix(token);
    }

    /**
     * 获取登录用户Token
     *
     * @return token
     */
    public static String getLoginUserToken() {

        return getUserTokenKey(getToken());
    }

    /**
     * 获取Time-Info
     *
     * @param request 请求
     * @return token
     */
    public static String getTimeInfo(ServerHttpRequest request) {
        return request.getHeaders().getFirst(SecurityConstants.Time_Info);
    }

    /**
     * 获取用户信息token
     *
     * @param token token
     * @return 用户key
     */
    public static String getUserTokenKey(String token) {
        return "user_key_" + token;
    }

    /**
     * 获取角色信息token
     *
     * @param token token
     * @return 角色key
     */
    public static String getRoleTokenKey(String token) {
        return "role_key_" + token;
    }

    /**
     * 获取菜单信息token
     *
     * @param token token
     * @return 菜单key
     */
    public static String getMenuTokenKey(String token) {
        return "menu_key_" + token;
    }
}
