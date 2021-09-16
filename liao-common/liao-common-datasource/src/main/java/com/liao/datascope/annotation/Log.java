package com.liao.datascope.annotation;

import com.liao.common.enums.BusinessType;
import com.liao.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义日志操作注解
 * </p>
 *
 * @author LiAo
 * @since 2020/12/16
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    // 模块
    public String title() default "";

    // 功能
    public BusinessType businessType() default BusinessType.OTHER;

    // 操作人类别
    public OperatorType operatorType() default OperatorType.MANAGE;

    // 是否保存请求的参数
    public boolean isSaveRequestData() default true;
}
