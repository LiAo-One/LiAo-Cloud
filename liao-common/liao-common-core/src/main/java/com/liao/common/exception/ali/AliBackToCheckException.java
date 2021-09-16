package com.liao.common.exception.ali;

import com.liao.common.exception.BusinessException;

/**
 * <p>
 * 阿里云请求失败异常
 * </p>
 *
 * @author LiAo
 * @since 2021/4/27
 */
public class AliBackToCheckException extends BusinessException {


    public AliBackToCheckException(String message) {
        super("阿里云操作异常：" + message);
    }
}
