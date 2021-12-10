package com.liao.system.api.factory;

import com.liao.common.core.R;
import com.liao.system.api.RemoteUserService;
import com.liao.system.api.entity.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户服务降级处理
 * </p>
 *
 * @author LiAo
 * @since 2021/12/8
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {

        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public String getLoginToken(String adminAccount, String adminPassword, String source) {
                return null;
            }

            @Override
            public List<SysMenu> getLoginUserMenu(String source) {
                return null;
            }

            @Override
            public R logout(String token, String source) {
                return null;
            }
        };
    }
}
