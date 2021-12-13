package com.liao.system.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 登录信息
 * </p>
 *
 * @author LiAo
 * @since 2021/12/10
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户唯一标识
    private String token;

    // 用户名id
    private Long userid;

    // 用户名
    private String username;

    // 登录时间
    private Long loginTime;

    // 过期时间
    private Long expireTime;

    // 登录IP地址
    private String ipaddr;

    // 登录地点
    private String loginLocation;

    // 浏览器类型
    private String browser;

    // 操作系统
    private String os;

    // 权限列表
    private Collection<String> permissions;

    // 角色列表
    private Collection<String> roles;

    // 登录用户
    private SysAdmin sysAdmin;

}
