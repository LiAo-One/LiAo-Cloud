package com.liao.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * <p>
 * 缓存主键生成策略
 * </p>
 *
 * @author LiAo
 * @since 2021/9/16
 */
@Configuration
public class CacheKeyConfig {

    @Bean("RedisCacheKeyGenerator")
    public KeyGenerator keyGenerator() {

        return (o, method, objects) -> {
            return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
        };
    }
}
