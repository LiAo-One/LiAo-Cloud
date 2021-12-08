package com.liao.auth.form;

import lombok.Data;

/**
 * <p>
 * 用户登录对象
 * </p>
 *
 * @author LiAo
 * @since 2021/12/8
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
