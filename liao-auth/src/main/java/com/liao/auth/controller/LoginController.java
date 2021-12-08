package com.liao.auth.controller;

import com.alibaba.nacos.api.common.Constants;
import com.liao.auth.form.LoginBody;
import com.liao.cache.utils.RedisUtil;
import com.liao.common.constant.SecurityConstants;
import com.liao.common.core.R;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.TokenUtil;
import com.liao.datascope.system.entity.SysMenu;
import com.liao.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 认证Controller
 * </p>
 *
 * @author LiAo
 * @since 2021/12/8
 */
@RestController
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 登录
     *
     * @param form 登录体
     * @return 结果
     */
    @PostMapping("login")
    public R login(@RequestBody LoginBody form) {
        String toke = remoteUserService.getLoginToken(form.getUsername(), form.getPassword(), SecurityConstants.INNER);
        R success = R.success();
        success.put(Constants.TOKEN, toke);
        return success;
    }

    /**
     * 获取登录用户信息
     *
     * @return 用户
     */
    @GetMapping("getInfo")
    public R getInfo() {

        String token = TokenUtil.getToken(ServletUtils.getRequest());

        R success = R.success();
        success.put("user", redisUtil.get(TokenUtil.getUserTokenKey(token)));
        success.put("role", redisUtil.get(TokenUtil.getRoleTokenKey(token)));
        success.put("permissions", Collections.singletonList("*:*:*"));

        return success;
    }

    @PostMapping("logout")
    public R logout() {
        String token = TokenUtil.getToken(ServletUtils.getRequest());
        return remoteUserService.logout(token,SecurityConstants.INNER);
    }
}
