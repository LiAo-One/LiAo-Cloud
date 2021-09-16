package com.liao.system.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.liao.common.core.R;
import com.liao.common.exception.user.LoginExpiredException;
import com.liao.common.utils.TokenUtil;
import com.liao.cache.utils.RedisUtil;
import com.liao.system.entity.SysAdmin;
import com.liao.system.entity.SysRole;
import com.liao.system.services.TokenCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenCheckServiceImpl implements TokenCheckService {

    @Autowired
    private RedisUtil redisUtil;


    private Object List;

    /**
     * Tokne 校验
     *
     * @param key Redis key
     * @return 结果
     */
    @Override
    public R verify(String key) {
        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }
        // 获取token
        String token = (String) redisUtil.get(key);

        // 执行校验
        return R.r(TokenUtil.verify(token));
    }

    /**
     * 根据Key获取Token数据
     *
     * @param name Token 数据头
     * @param key  Redis-key
     * @return 结果
     */
    @Override
    public String getTokenMes(String name, String key) {

        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }

        // 获取token
        String token = (String) redisUtil.get(key);

        return TokenUtil.getTokenMes(name, token);
    }

    /**
     * 获取当前登录用户数据
     *
     * @param key token
     * @return 数据
     */
    @Override
    public R getTokenMesAll(String key) {
        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }

        // 获取token
        String token = (String) redisUtil.get(key);

        Map<String, Object> map = new HashMap<>();

        String user = TokenUtil.getTokenMes("user", token);
        String role = TokenUtil.getTokenMes("role", token);
        String menus = TokenUtil.getTokenMes("menu", token);

        JSONObject userJos = JSONObject.parseObject(user);
        JSONObject roleJos = JSONObject.parseObject(role);

        map.put("user", JSONObject.toJavaObject(userJos, SysAdmin.class));
        map.put("role", JSONObject.toJavaObject(roleJos, SysRole.class));

        return R.success(menus,map);
    }
}
