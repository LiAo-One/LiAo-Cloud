package com.liao.system.api;

import com.liao.common.constant.SecurityConstants;
import com.liao.common.constant.ServiceNameConstants;
import com.liao.common.core.R;
import com.liao.system.api.entity.SysLogininfor;
import com.liao.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * <p>
 *
 * </p>
 *
 * @author LiAo
 * @since 2021/12/13
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE,
        fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteLogService {


    @PostMapping("/sys-logininfor/add")
    R saveLogininfor(@RequestBody SysLogininfor sysLogininfor,
                     @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
