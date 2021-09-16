package com.liao.common.utils;

import com.alibaba.fastjson.JSON;
import com.liao.common.core.R;
import com.liao.common.core.text.Convert;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 客户客户端工具类
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class ServletUtils {


    /**
     * 获取String类型参数
     *
     * @param name key
     * @return value
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }


    /**
     * 获取Integer类型参数
     *
     * @param name key
     * @return 参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取 request
     *
     * @return request;
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 设置webflus 模型
     *
     * @param response ServerHttpResponse
     * @param value    内容
     * @return 结果
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, 500);
    }

    /**
     * 设置webflus 模型
     *
     * @param response ServerHttpResponse
     * @param value    内容
     * @param code     状态码
     * @return 结果
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, code);
    }

    /**
     * 设置webflus 模型
     *
     * @param response ServerHttpResponse
     * @param status   http 状态码
     * @param value    内容
     * @param code     状态码
     * @return 结果
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value,
                                                   int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflus 模型
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http 状态码
     * @param value       内容
     * @param code        状态码
     * @return 结果
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status,
                                                   Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        R success = R.success(code, value.toString());
        DataBuffer wrap = response.bufferFactory().wrap(JSON.toJSONBytes(success));
        return response.writeWith(Mono.just(wrap));
    }
}
