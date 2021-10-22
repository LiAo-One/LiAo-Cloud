package com.liao.system.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.liao.common.core.R;
import com.liao.common.exception.user.LoginExpiredException;
import com.liao.common.utils.TokenUtil;
import com.liao.cache.utils.RedisUtil;
import com.liao.datascope.system.entity.SysMenu;
import com.liao.system.dao.SysRoleMapper;
import com.liao.system.entity.SysAdmin;
import com.liao.system.entity.SysRole;
import com.liao.system.entity.vo.RouterVo;
import com.liao.system.services.SysMenuService;
import com.liao.system.services.TokenCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenCheckServiceImpl implements TokenCheckService {

    @Autowired
    private RedisUtil redisUtil;

    // 角色
    @Autowired
    private SysRoleMapper sysRoleMapper;

    // 按钮
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * Tokne 校验
     *
     * @param key Redis key
     * @return 结果
     */
    @Override
    public R verify(String key) {
        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }
        // 获取token
        String token = (String) redisUtil.get(key);

        // 执行校验
        return R.r(TokenUtil.verify(token));
    }

    /**
     * 根据Key获取Token数据
     *
     * @param key  Redis-key
     * @return 结果
     */
    @Override
    public List<RouterVo> getTokenMes(String key) {

        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }

        SysRole sysRole = (SysRole)redisUtil.get(TokenUtil.getRoleTokenKey(key));

        // userMenu
        java.util.List<SysMenu> menus = sysMenuService.selectLoginMenuList(sysRole.getRoleId());

        // 转换为路由
        return sysMenuService.buildMenus(menus);
    }

    /**
     * 获取当前登录用户数据
     *
     * @param key token
     * @return 数据
     */
    @Override
    public R getTokenMesAll(String key) {
        // 根据key获取Token
        if (!redisUtil.hasKey(key)) {
            throw new LoginExpiredException();
        }

        Map<String, Object> map = new HashMap<>();

        map.put("user", redisUtil.get(TokenUtil.getUserTokenKey(key)));
        map.put("role", redisUtil.get(TokenUtil.getRoleTokenKey(key)));
        return R.success(map);
    }
}
