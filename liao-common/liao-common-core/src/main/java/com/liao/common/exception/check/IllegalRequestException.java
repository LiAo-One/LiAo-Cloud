package com.liao.common.exception.check;

import com.liao.common.exception.BusinessException;

public class IllegalRequestException extends BusinessException {
    public IllegalRequestException() {
        super("非法请求！！ 滚出克");
    }
}
