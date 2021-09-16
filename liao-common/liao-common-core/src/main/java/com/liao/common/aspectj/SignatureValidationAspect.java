package com.liao.common.aspectj;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.liao.common.constant.Constants;
import com.liao.common.constant.SecurityConstants;
import com.liao.common.core.text.Convert;
import com.liao.common.exception.check.IllegalRequestException;
import com.liao.common.utils.DateUtils;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.StringUtils;
import com.liao.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 校验注解
 * </p>
 *
 * @author LiAo
 * @since 2020/12/21
 */
@Slf4j
@Aspect
@Component
public class SignatureValidationAspect {

    // 请求最小限制
    private static final int MAX_REQUEST = 15;

    //配置织入点
    @Pointcut("@annotation(com.liao.common.annotation.SignatureValidation)")
    public void verifyUserKey() {
    }

    /**
     * 切点之前织入 校验签名
     */
    @Before("verifyUserKey()")
    public void doBasicProfiling() {

        // 需要校验的Token
        String token = ServletUtils.getRequest().getHeader(SecurityConstants.X_SIGN);
        // 时间戳
        String timeInfo = ServletUtils.getRequest().getHeader(SecurityConstants.Time_Info);

        // 格式化后的参数
        String asciiSort = setRequestValue();

        log.info("=========parmJSON===>{}", asciiSort);

        // 判断token是否为空
        if (StringUtils.isEmpty(token)) {
            throw new IllegalRequestException();
        }

        // 判断timeInfo是否为空
        if (StringUtils.isEmpty(timeInfo)) {
            throw new IllegalRequestException();
        }

        // 生成MD5 Token
        String newToken = SecureUtil.md5(timeInfo + asciiSort + Constants.SECRET);

        /*log.info("============>timeInfo:{}====>asciiSort:{}====>:Constants.SECRET{}",
                timeInfo, asciiSort, Constants.SECRET);*/

        // 校验Token
        if (!newToken.equals(token)) {
            throw new IllegalRequestException();
        }
        // 当前时间
        Date newData = DateUtils.parseDate(DateUtils.getTime());
        // 请求携带的时间
        Date InfoData = DateUtils.parseDate(timeInfo);

        // 判断是否超时
        if (MAX_REQUEST < DateUtils.getDatePoorNs(newData, InfoData)) {
            throw new IllegalRequestException();
        }
    }


    /**
     * 获取请求的参数 字符串
     */
    private String setRequestValue() {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        // 获取排序后的字符串
        return getAsciiSort(map);
    }

    /**
     * Map 根据Key ASCII码排序
     *
     * @param map 要排序的
     * @return 字符串
     */
    public static String getAsciiSort(Map<String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }

        Set<String> strings = map.keySet();

        TreeMap<String, String[]> stringTreeMap = new TreeMap<>();

        for (String string : strings) {
            stringTreeMap.put(string, map.get(string));
        }


        log.info(JSON.toJSONString(stringTreeMap));

        return Convert.toMapString(stringTreeMap);
    }
}

