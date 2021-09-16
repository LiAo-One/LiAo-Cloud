package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.datascope.annotation.Log;
import com.liao.system.entity.SysAdminRole;
import com.liao.system.services.SysAdminRoleService;
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
 * 管理员角色关联表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/sys-admin-role")
@Api(tags = {"管理员角色关联表"})
public class SysAdminRoleController {

    @Autowired
    private SysAdminRoleService sysAdminRoleService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysAdminRole recode) {
        return sysAdminRoleService.selPage(recode);
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
        return sysAdminRoleService.findById(id);
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
        return sysAdminRoleService.findByIds(ids);
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
    @Log(title = "管理员角色关联表", businessType = BusinessType.INSERT)
    public R add(SysAdminRole recode) {
        return sysAdminRoleService.add(recode);
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
    @Log(title = "管理员角色关联表", businessType = BusinessType.UPDATE)
    public R upd(SysAdminRole recode) {
        return sysAdminRoleService.updById(recode);
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
    @Log(title = "管理员角色关联表", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysAdminRoleService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "管理员角色关联表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysAdminRoleService.deletes(ids);
    }
}
