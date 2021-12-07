package com.liao.gatewary.service;

import com.liao.common.core.R;
import com.liao.common.exception.CaptchaException;

import java.io.IOException;

/**
 * <p>
 * 验证码处理
 * </p>
 *
 * @author LiAo
 * @since 2021/12/7
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     */
    R createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCapcha(String key, String value) throws CaptchaException;
}
