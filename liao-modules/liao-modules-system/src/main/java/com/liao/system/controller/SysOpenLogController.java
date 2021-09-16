package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.datascope.system.entity.SysOpenLog;
import com.liao.datascope.system.services.SysOpenLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/sys-open-log")
@Api(tags = {"操作日志记录"})
public class SysOpenLogController {

    @Autowired
    private SysOpenLogService sysOpenLogService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysOpenLog recode) {
        return sysOpenLogService.selPage(recode);
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
        return sysOpenLogService.findById(id);
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
        return sysOpenLogService.findByIds(ids);
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
    @Log(title = "操作日志记录", businessType = BusinessType.INSERT)
    public R add(SysOpenLog recode) {
        return sysOpenLogService.add(recode);
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
    @Log(title = "操作日志记录", businessType = BusinessType.UPDATE)
    public R upd(SysOpenLog recode) {
        return sysOpenLogService.updById(recode);
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
    @Log(title = "操作日志记录", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysOpenLogService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "操作日志记录", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysOpenLogService.deletes(ids);
    }

    /**
     * 清空操作日志
     *
     * @return 结果
     */
    @DeleteMapping("clean")
    @ApiOperation("清空操作日志")
    @SignatureValidation
    @Log(title = "清空操作日志", businessType = BusinessType.CLEAN)
    public R clean() {
        return sysOpenLogService.clean();
    }
}
