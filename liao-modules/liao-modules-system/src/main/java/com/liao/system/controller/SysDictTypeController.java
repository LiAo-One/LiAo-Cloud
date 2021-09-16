package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.system.entity.SysDictType;
import com.liao.system.services.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/sys-dict-type")
@Api(tags = {"字典类型表"})
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysDictType recode) {
        return sysDictTypeService.selPage(recode);
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
        return sysDictTypeService.findById(id);
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
        return sysDictTypeService.findByIds(ids);
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
    @Log(title = "字典类型表", businessType = BusinessType.INSERT)
    public R add(SysDictType recode) {
        return sysDictTypeService.add(recode);
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
    @Log(title = "字典类型表", businessType = BusinessType.UPDATE)
    public R upd(SysDictType recode) {
        return sysDictTypeService.updById(recode);
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
    @Log(title = "字典类型表", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysDictTypeService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @SignatureValidation
    @Log(title = "字典类型表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysDictTypeService.deletes(ids);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    @ApiOperation("字典数据信息")
    public R dictType(@PathVariable String dictType) {
        return sysDictTypeService.selectDictDataByType(dictType);
    }

    @GetMapping("optionselect")
    @ApiOperation("获取字典选择框列表")
    public R optionselect() {
        return sysDictTypeService.selectDictTypeAll();
    }
}
