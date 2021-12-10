package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.constant.UserConstants;
import com.liao.common.core.R;
import com.liao.system.api.entity.SysMenu;
import com.liao.system.entity.vo.TreeSelect;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.system.dao.SysMenuMapper;
import com.liao.system.dao.SysRoleMapper;
import com.liao.system.entity.vo.MetaVo;
import com.liao.system.entity.vo.RouterVo;
import com.liao.system.services.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据角色id查询菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectLoginMenuList(Long roleId) {

        List<SysMenu> menus = sysMenuMapper.selectLoginMenuList(roleId);

        return getChildPerms(menus, 0);
    }

    /**
     * 加载角色菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(Long roleId) {
        return sysMenuMapper.selectLoginMenuList(roleId);
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuListAll() {
        return sysMenuMapper.selectList(null);
    }

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysMenu recode) {
        // 分页信息
        /*IPage<SysMenu> page = PageUtils.startPage();*/

        // 构造条件
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();

        // 菜单IDSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getMenuId()), "menu_id", recode.getMenuId());
        // 菜单名称SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getMenuName()), "menu_name", recode.getMenuName());
        // 父菜单IDSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getParentId()), "parent_id", recode.getParentId());
        // 显示顺序SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOrderNum()), "order_num", recode.getOrderNum());
        // 路由地址SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getPath()), "path", recode.getPath());
        // 组件路径SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getComponent()), "component", recode.getComponent());
        // 是否为外链（0是 1否）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsFrame()), "is_frame", recode.getIsFrame());
        // 是否缓存（0缓存 1不缓存）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsCache()), "is_cache", recode.getIsCache());
        // 菜单类型（M目录 C菜单 F按钮）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getMenuType()), "menu_type", recode.getMenuType());
        // 菜单状态（0显示 1隐藏）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getVisible()), "visible", recode.getVisible());
        // 菜单状态（0正常 1停用）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getStatus()), "status", recode.getStatus());
        // 权限标识SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getPerms()), "perms", recode.getPerms());
        // 菜单图标SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getIcon()), "icon", recode.getIcon());
        // 创建时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 更新时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<SysMenu> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysMenuMapper.selectList(wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        return R.r(sysMenuMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysMenuMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysMenu recode) {
        return R.r(sysMenuMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysMenu recode) {
        if (StringUtils.isEmpty(recode.getMenuId())) {
            throw new MissingParametersException("菜单表ID");
        }
        return R.r(sysMenuMapper.updateById(recode));
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R delete(Long id) {
        if (StringUtils.isEmpty(id)) {
            throw new MissingParametersException("菜单表ID");
        }
        return R.r(sysMenuMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysMenuMapper.deleteBatchIds(ids));
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {

        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (sysMenu.getParentId() == parentId) {
                recursionFn(list, sysMenu);
                returnList.add(sysMenu);
            }
        }

        return returnList;
    }

    /**
     * 递归单个父节点列表
     *
     * @param list 所有节点
     * @param t    父节点
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        // 存入父节点
        t.setChildren(childList);
        // 遍历存入的子节点
        for (SysMenu tChild : childList) {
            // 判断是否有子节点
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 根据父节点得到子节点列表
     *
     * @param list 所有按钮
     * @param t    单个父节点
     * @return 子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tList = new ArrayList<SysMenu>();
        for (SysMenu n : list) {
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tList.add(n);
            }
        }
        return tList;
    }

    /**
     * 判断是否有子节点
     *
     * @param list 所有节点
     * @param t    要判断的
     * @return true：有 ？ false：没有
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 构建Vue 路由菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {

        List<RouterVo> routerVos = new LinkedList<>();
        System.out.println(menus);
        for (SysMenu menu : menus) {
            RouterVo routerVo = new RouterVo();
            // 是否隐藏
            routerVo.setHidden("1".equals(menu.getVisible()));
            // 路由名称
            routerVo.setName(getRouteName(menu));
            // 路由路径
            routerVo.setPath(getRouterPath(menu));
            // 子组件地址
            routerVo.setComponent(getComponent(menu));
            // 其他元素
            routerVo.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
            List<SysMenu> cMenus = menu.getChildren();
            if (StringUtils.isNotEmpty(cMenus) && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                routerVo.setAlwaysShow(true);
                routerVo.setRedirect("noRedirect");
                routerVo.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
                childrenList.add(children);
                routerVo.setChildren(childrenList);
            }

            routerVos.add(routerVo);
        }
        return routerVos;
    }

    /**
     * 根据角色id查询菜单树信息
     *
     * @param roleId 角色id
     * @return 选中的菜单树列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return sysMenuMapper.selectMenuListByRoleId(roleId);
    }

    /**
     * 构建下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {

        List<SysMenu> sysMenus = buildMenuTree(menus);

        return sysMenus.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for (SysMenu menu : menus) {
            tempList.add(menu.getMenuId());
        }

        for (SysMenu menu : menus) {
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }

        if (StringUtils.isEmpty(returnList)) {
            returnList = menus;
        }
        return returnList;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu) {
        // 路径首字母大写
        String routerName = StringUtils.capitalize(menu.getPath());
        // 不是外链且是一级目录
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }

        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 判断菜单内部是否跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsCache().equals(UserConstants.NO_FRAME);
    }

    /**
     * 获得子组件地址
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }

        return component;
    }
}
