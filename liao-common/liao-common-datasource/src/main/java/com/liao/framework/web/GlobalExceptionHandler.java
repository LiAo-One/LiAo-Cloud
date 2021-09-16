package com.liao.framework.web;

import com.liao.common.core.R;
import com.liao.common.exception.CustomException;
import com.liao.common.exception.ali.AliBackToCheckException;
import com.liao.common.exception.user.LoginExpiredException;
import com.liao.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     *
     * @param e 异常
     * @return 异常处理信息
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return R.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 未知运行异常
     *
     * @param e 异常
     * @return 异常处理信息
     */
    @ExceptionHandler({RuntimeException.class})
    public R notFount(RuntimeException e) {
        log.error(e.getMessage(), e);
        return R.error("运行时异常" + e.getMessage());
    }

    /**
     * 未知运行异常
     *
     * @param e 异常
     * @return 异常处理信息
     */
    @ExceptionHandler({AliBackToCheckException.class})
    public R AliOperation(AliBackToCheckException e) {
        log.error(e.getMessage(), e);
        return R.error("阿里巴巴操作异常" + e.getMessage());
    }

    /**
     * 参数校验
     *
     * @param ex 异常
     * @return 结果
     */
    /*@ExceptionHandler(value = ConstraintViolationException.class)
    public R handle1(ConstraintViolationException ex) {
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        log.error(msg.toString(), ex);
        // 注意：Response类必须有get和set方法，不然会报错
        return R.error(msg.toString());
    }*/

    /**
     * 对象校验
     *
     * @param ex 异常
     * @return 结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handle2(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            log.error(ex.getMessage(), ex);
            return R.error(field + ":" + defaultMessage);
        } else {
            log.error(ex.getMessage(), ex);
            return R.error(ex.getMessage());
        }
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R businessException(CustomException e) {
        if (StringUtils.isNull(e.getCode())) {
            return R.error(e.getMessage());
        }
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 未登录
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler({LoginExpiredException.class})
    public R LoginExpiredException(Exception e) {
        log.error(e.getMessage(), e);
        return R.logout();
    }

    /**
     * 系统异常
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler({Exception.class})
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error("服务器错误，请联系管理员");
    }
}
