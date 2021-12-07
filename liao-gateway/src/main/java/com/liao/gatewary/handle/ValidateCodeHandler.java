package com.liao.gatewary.handle;

import com.liao.common.core.R;
import com.liao.gatewary.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 验证码获取
 * </p>
 *
 * @author LiAo
 * @since 2021/12/7
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        R success;
        try {
            success = validateCodeService.createCapcha();
        } catch (Exception e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(success));
    }
}
