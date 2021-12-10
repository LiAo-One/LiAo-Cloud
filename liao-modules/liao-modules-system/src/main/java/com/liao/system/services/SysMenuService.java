package com.liao.system.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.system.api.entity.SysMenu;
import com.liao.system.entity.vo.TreeSelect;
import com.liao.system.entity.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据角色id查询菜单树
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<SysMenu> selectLoginMenuList(Long roleId);

    /**
     * 加载角色菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(Long roleId);

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListAll();

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(SysMenu recode);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    R findById(Long id);

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    R findByIds(List<Long> ids);

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    R add(SysMenu recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(SysMenu recode);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    R delete(Long id);

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    R deletes(List<Long> ids);

    /**
     * 构建Vue 路由菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);

    /**
     * 根据角色id查询菜单树信息
     *
     * @param roleId 角色id
     * @return 选中的菜单树列表
     */
    List<Long> selectMenuListByRoleId(Long roleId);

    /**
     * 构建下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * 构建树结构
     *
     * @param menus 菜单列表
     * @return 数结构列表
     */
    List<SysMenu> buildMenuTree(List<SysMenu> menus);
}
