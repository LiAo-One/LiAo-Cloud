package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.datascope.annotation.Log;
import com.liao.system.entity.SysAdmin;
import com.liao.system.services.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-14
 */
@Validated
@RestController
@Api(tags = "管理员")
@RequestMapping("/sys-admin")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;

    /**
     * 管理员登录
     *
     * @param adminAccount  账号
     * @param adminPassword 密码
     * @return 结果
     */
    @PostMapping("login")
    @ApiOperation("管理员登录")
    @SignatureValidation
    public R login(String adminAccount, String adminPassword) {

        String login = sysAdminService.login(adminAccount, adminPassword);
        R success = R.success();

        return success;
    }

    /**
     * 退出登录
     *
     * @param token token
     * @return 结果
     */
    @PostMapping("logout")
    @ApiOperation("管理员退出登录")
    public R logout(String token) {
        return sysAdminService.logout(token);
    }

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysAdmin recode) {
        return sysAdminService.selPage(recode);
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("sel_id")
    @ApiOperation("根据id查询数据")
    @SignatureValidation
    public R findById(Long id) {
        return sysAdminService.findById(id);
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
        return sysAdminService.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @ApiOperation("添加数据")
    @Log(title = "管理员", businessType = BusinessType.INSERT)
    public R add(SysAdmin recode, @RequestParam(value = "roleId", required = false) Long roleId) {
        return sysAdminService.add(recode, roleId);
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @PostMapping("upd_id")
    @ApiOperation("根据id修改")
    @Log(title = "管理员", businessType = BusinessType.UPDATE)
    public R upd(SysAdmin recode, Long roleId) {
        return sysAdminService.updById(recode, roleId);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("del_id")
    @ApiOperation("根据id删除")
    @Log(title = "管理员", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysAdminService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "管理员", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysAdminService.deletes(ids);
    }
}

