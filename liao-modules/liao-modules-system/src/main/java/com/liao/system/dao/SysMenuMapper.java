package com.liao.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.datascope.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色id查询菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<SysMenu> selectLoginMenuList(@Param("roleId") Long roleId);

    /**
     * 根据id插叙菜单树信息
     *
     * @param roleId 角色id
     * @return 选中菜单列表
     */
    List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId);
}
