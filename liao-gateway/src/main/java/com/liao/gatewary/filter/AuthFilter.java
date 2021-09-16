package com.liao.gatewary.filter;

import com.liao.common.constant.HttpStatus;
import com.liao.common.constant.SecurityConstants;
import com.liao.common.utils.SecurityUtils;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.StringUtils;
import com.liao.common.utils.TokenUtil;
import com.liao.gatewary.config.properties.IgnoreWhiteProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 权限校验
 * </p>
 *
 * @author LiAo
 * @since 2021/9/15
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    // 白名单
    @Autowired
    private IgnoreWhiteProperties whiteProperties;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, whiteProperties.getWhites())) {
            return chain.filter(exchange);
        }

        // 获取Token
        String token = TokenUtil.getToken(request);

        // 校验
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange, "Token不能为空");
        }

        // 获取Sing
        String sign = TokenUtil.getXSign(request);

        // 校验
        if (StringUtils.isEmpty(sign)) {
            return unauthorizedResponse(exchange, "Not Acceptable");
        }

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    // 鉴权异常
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED);
    }



    @Override
    public int getOrder() {
        return -200;
    }
}
