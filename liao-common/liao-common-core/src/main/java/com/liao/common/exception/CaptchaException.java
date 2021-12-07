package com.liao.common.exception;

/**
 * <p>
 * 验证码错误异常
 * </p>
 *
 * @author LiAo
 * @since 2021/12/7
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg)
    {
        super(msg);
    }
}
