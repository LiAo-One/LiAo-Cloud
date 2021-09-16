package com.liao.common.exception.iot;

import com.liao.common.exception.BusinessException;

/**
 * <p>
 * 物模型标识符不唯一
 * </p>
 *
 * @author LiAo
 * @since 2021/6/3
 */
public class TmMarkIsItNotUniqueException extends BusinessException {


    public TmMarkIsItNotUniqueException() {
        super("物模型标识不唯一");
    }
}
