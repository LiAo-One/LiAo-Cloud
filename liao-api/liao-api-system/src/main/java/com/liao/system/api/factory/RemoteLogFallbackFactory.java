package com.liao.system.api.factory;

import com.liao.common.core.R;
import com.liao.system.api.RemoteLogService;
import com.liao.system.api.RemoteUserService;
import com.liao.system.api.entity.SysLogininfor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author LiAo
 * @since 2021/12/13
 */
@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {
    @Override
    public RemoteLogService create(Throwable cause) {

        log.error("日志服务调用失败:{}", cause.getMessage());

        return new RemoteLogService() {
            @Override
            public R saveLogininfor(SysLogininfor sysLogininfor, String source) {
                return null;
            }
        };
    }
}
