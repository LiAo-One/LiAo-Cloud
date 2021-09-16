package com.liao.common.exception;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
