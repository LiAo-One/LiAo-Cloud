package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.system.entity.SysRole;
import com.liao.system.services.SysRoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/sys-role")
@Api(tags = {"角色表"})
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysRole recode) {
        return sysRoleService.selPage(recode);
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("sel_id")
    @ApiOperation("根据id查询数据")
    public R findById(@RequestParam(value = "id", required = false) Long id) {
        return sysRoleService.findById(id);
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
        return sysRoleService.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @ApiOperation("添加数据")
    @Log(title = "角色表", businessType = BusinessType.INSERT)
    public R add(SysRole recode, @RequestParam(value = "menuIds", required = false) List<Long> menuIds) {
        return sysRoleService.add(recode, menuIds);
    }

    /**
     * 根据id修改
     *
     * @param recode  修改参数
     * @param menuIds 按钮id
     * @return 结果
     */
    @PostMapping("upd_id")
    @ApiOperation("根据id修改")
    @Log(title = "角色表", businessType = BusinessType.UPDATE)
    public R upd(SysRole recode, @RequestParam(value = "menuIds", required = false) List<Long> menuIds) {
        return sysRoleService.updById(recode, menuIds);
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
    @Log(title = "角色表", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysRoleService.delete(id);
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
    @Log(title = "角色表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysRoleService.deletes(ids);
    }
}
