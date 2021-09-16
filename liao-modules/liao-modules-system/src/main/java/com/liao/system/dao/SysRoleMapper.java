package com.liao.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询登录用户的角色信息
     *
     * @param userId 用户id
     * @return 角色信息
     */
    SysRole selLoginUserRole(@Param("userId") Long userId);
}
