package com.liao.system.api;

import com.liao.common.constant.SecurityConstants;
import com.liao.common.constant.ServiceNameConstants;
import com.liao.common.core.R;
import com.liao.system.api.entity.SysMenu;
import com.liao.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * <p>
 * 用户服务
 * </p>
 *
 * @author LiAo
 * @since 2021/12/8
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE,
        fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param adminAccount  用户名
     * @param adminPassword 密码
     * @param source        请求来源
     * @return 结果
     */
    @PostMapping("/sys-admin/get_login_token")
    String getLoginToken(@RequestParam(name = "adminAccount") String adminAccount,
                         @RequestParam(name = "adminPassword") String adminPassword,
                         @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 获取登录用户按钮
     *
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/token/get-login-user-menu")
    List<SysMenu> getLoginUserMenu(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 退出登录
     *
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/sys-admin/logout")
    R logout(@RequestParam(name = "token") String token, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
