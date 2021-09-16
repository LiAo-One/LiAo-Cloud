package com.liao.common.exception.check;

import com.liao.common.exception.BusinessException;


/**
 * <p>
 * 参数缺失异常
 * </p>
 *
 * @author LiAo
 * @since 2020/11/17
 */
public class MissingParametersException extends BusinessException {


    public MissingParametersException(String message) {
        super("参数" + message + "缺失");
    }
}
