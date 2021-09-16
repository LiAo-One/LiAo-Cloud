package com.liao.common.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.liao.common.exception.ali.AliBackToCheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>
 * 阿里云返回值校验注解处理
 * </p>
 *
 * @author LiAo
 * @since 2021/4/27
 */
@Aspect
@Component
public class AliBackToCheckAspect {

    // 设置织入点
    @Pointcut("@annotation(com.liao.common.annotation.AliBackToCheck)")
    public void checkPointCut() {
    }


    /**
     * 环切
     *
     * @param proceedingJoinPoint 请求返回参数
     * @return 格式化后的参数
     */
    @Around("checkPointCut()")
    public JSONObject handlerController(ProceedingJoinPoint proceedingJoinPoint) {

        String success = null;

        JSONObject jsonObject = null;

        try {
            //获取方法的执行结果
            Object proceed = proceedingJoinPoint.proceed();

            // 转为JSON
            jsonObject = (JSONObject) JSONObject.toJSON(proceed);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        if (jsonObject == null) {
            throw new AliBackToCheckException("请求失败，请联系管理员");
        }

        // 获取身体
        JSONObject bodyJOSN = jsonObject.getJSONObject("body");
        // 存储data
        JSONObject dataJSON = null;

        // 存储errorMessage
        String errorMessage = null;

        // 为空标识为Success
        if (bodyJOSN == null) {
            // 直接获取 Success
            success = String.valueOf(jsonObject.get("Success"));
            // 获取Data
            dataJSON = jsonObject.getJSONObject("Data");
        } else {
            // 获取执行结果
            success = String.valueOf(bodyJOSN.get("success"));
            // 获取data
            dataJSON = bodyJOSN.getJSONObject("data");
            // 获取 errorMessage
            errorMessage = bodyJOSN.getString("errorMessage");
        }

        // 请求失败抛出异常
        if (success != null && success.equals("false")) {
            throw new AliBackToCheckException(errorMessage + "===>>>" + Arrays.toString(proceedingJoinPoint.getArgs()));
        }

        // 如果dataJSON 为空说明没有data
        if (dataJSON == null) {
            return bodyJOSN;
        }

        // 环切 返回值
        return dataJSON;
    }
}
