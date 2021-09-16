package com.liao.system.services;

import com.liao.common.core.R;

/**
 * <p>
 * Token 校验接口
 * </p>
 *
 * @author LiAo
 * @since 2020/12/18
 */
public interface TokenCheckService {
    /**
     * Tokne 校验
     *
     * @param key Redis key
     * @return 结果
     */
    R verify(String key);


    /**
     * 根据Key获取Token数据
     *
     * @param name Token 数据头
     * @param key  Redis-key
     * @return 结果
     */
    String getTokenMes(String name, String key);

    /**
     * 获取当前登录用户数据
     *
     * @param key token
     * @return 数据
     */
    R getTokenMesAll(String key);
}
