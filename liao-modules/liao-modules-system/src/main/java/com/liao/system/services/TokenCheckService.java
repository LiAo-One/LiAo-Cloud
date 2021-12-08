package com.liao.system.services;

import com.liao.common.core.R;
import com.liao.datascope.system.entity.SysMenu;
import com.liao.system.entity.vo.RouterVo;

import java.util.List;

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
     * @param key  Redis-key
     * @return 结果
     */
    List<RouterVo> getTokenMes(String key);

    /**
     * 获取当前登录用户数据
     *
     * @param key token
     * @return 数据
     */
    R getTokenMesAll(String key);

    /**
     * 根据用户信息查询菜单树
     *
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId();
}
