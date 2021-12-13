package com.liao.auth.service;

import com.liao.common.constant.Constants;
import com.liao.common.constant.SecurityConstants;
import com.liao.common.exception.ServiceException;
import com.liao.common.utils.SecurityUtils;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.ip.IpUtils;
import com.liao.system.api.RemoteLogService;
import com.liao.system.api.RemoteUserService;
import com.liao.system.api.entity.LoginUser;
import com.liao.system.api.entity.SysAdmin;
import com.liao.system.api.entity.SysLogininfor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 登录校验方法
 * </p>
 *
 * @author LiAo
 * @since 2021/12/13
 */
@Component
public class SysLoginService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 登录校验方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }

        LoginUser info = remoteUserService.info(username, SecurityConstants.INNER);

        SysAdmin sysAdmin = info.getSysAdmin();

        if (!SecurityUtils.matchesPassword(password, sysAdmin.getAdminPassword())) {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码错误");
            throw new ServiceException("用户不存在/密码错误");
        }
        recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return info;
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     */
    public void recordLogininfor(String username, String status, String message) {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus("0");
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus("1");
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
