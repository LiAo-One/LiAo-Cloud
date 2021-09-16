package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.system.entity.SysRoleMenu;
import com.liao.system.services.SysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/sys-role-menu")
@Api(tags = {"角色菜单关联表"})
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysRoleMenu recode) {
        return sysRoleMenuService.selPage(recode);
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("sel_id")
    @ApiOperation("根据id查询数据")
    public R findById(Long id){
        return sysRoleMenuService.findById(id);
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @PostMapping("sel_ids")
    @ApiOperation("根据ids批量查询")
    public R findByIds(@RequestParam("ids") List<Long> ids){
        return sysRoleMenuService.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @SignatureValidation
    @ApiOperation("添加数据")
    @Log(title = "角色菜单关联表",businessType = BusinessType.INSERT)
    public R add(SysRoleMenu recode) {
        return sysRoleMenuService.add(recode);
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @PostMapping("upd_id")
    @SignatureValidation
    @ApiOperation("根据id修改")
    @Log(title = "角色菜单关联表",businessType = BusinessType.UPDATE)
    public R upd(SysRoleMenu recode) {
        return sysRoleMenuService.updById(recode);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("del_id")
    @SignatureValidation
    @ApiOperation("根据id删除")
    @Log(title = "角色菜单关联表", businessType = BusinessType.DELETE)
    public R delete(Long id){
        return sysRoleMenuService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @SignatureValidation
    @ApiOperation("根据ids批量删除")
    @Log(title = "角色菜单关联表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids){
        return sysRoleMenuService.deletes(ids);
    }
}
