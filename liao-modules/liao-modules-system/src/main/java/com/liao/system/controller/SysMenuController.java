package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.system.api.entity.SysMenu;
import com.liao.system.entity.vo.RouterVo;
import com.liao.system.services.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/sys-menu")
@Api(tags = {"菜单表"})
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysMenu recode) {
        return sysMenuService.selPage(recode);
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @PostMapping("sel_menu_all")
    @ApiOperation("查询所有菜单")
    public List<SysMenu> selectMenuListAll() {
        return sysMenuService.selectMenuListAll();
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("sel_id")
    @ApiOperation("根据id查询数据")
    public R findById(Long id) {
        return sysMenuService.findById(id);
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @PostMapping("sel_ids")
    @ApiOperation("根据ids批量查询")
    public R findByIds(@RequestParam("ids") List<Long> ids) {
        return sysMenuService.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @ApiOperation("添加数据")
    @SignatureValidation
    @Log(title = "菜单表", businessType = BusinessType.INSERT)
    public R add(SysMenu recode) {
        return sysMenuService.add(recode);
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @PostMapping("upd_id")
    @ApiOperation("根据id修改")
    @SignatureValidation
    @Log(title = "菜单表", businessType = BusinessType.UPDATE)
    public R upd(SysMenu recode) {
        return sysMenuService.updById(recode);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("del_id")
    @ApiOperation("根据id删除")
    @SignatureValidation
    @Log(title = "菜单表", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysMenuService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "菜单表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysMenuService.deletes(ids);
    }

    /**
     * 加载角色对应的菜单树列表
     *
     * @param roleId 角色id
     * @return 菜单树
     */
    @PostMapping("role_menu_tree_select")
    @ApiOperation("加载角色对应的菜单树列表")
    public R roleMenuTreeselect(Long roleId) {
        List<SysMenu> menus = selectMenuListAll();
        R r = new R();
        r.put("checkedKeys", sysMenuService.selectMenuListByRoleId(roleId));
        r.put("menus", sysMenuService.buildMenuTreeSelect(menus));
        return r;
    }

    /**
     * 加载用户路由
     *
     * @param sysMenus 按钮
     * @return 路由
     */
    @GetMapping("get-router")
    @ApiOperation("加载角色对应的菜单树列表")
    public List<RouterVo> getUserRouterVo(List<SysMenu> sysMenus) {
        return sysMenuService.buildMenus(sysMenus);
    }

    /**
     * 加载菜单树列表
     *
     * @return 菜单树
     */
    @GetMapping("menu_tree_select")
    @ApiOperation("加载菜单树列表")
    public R roleMenuTreeselect() {
        List<SysMenu> menus = selectMenuListAll();
        return R.success(sysMenuService.buildMenuTreeSelect(menus));
    }
}
