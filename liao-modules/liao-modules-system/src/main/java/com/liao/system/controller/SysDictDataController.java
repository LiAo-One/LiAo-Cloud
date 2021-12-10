package com.liao.system.controller;

import com.liao.common.annotation.SignatureValidation;
import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.common.enums.BusinessType;
import com.liao.system.api.entity.SysDictData;
import com.liao.system.services.SysDictDataService;
import com.liao.system.services.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/sys-dict-data")
@Api(tags = {"字典数据表"})
public class SysDictDataController {

    @Autowired
    private SysDictDataService sysDictDataService;

    @Autowired
    private SysDictTypeService selectDictDataByType;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(SysDictData recode) {
        return sysDictDataService.selPage(recode);
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
        return sysDictDataService.findById(id);
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
        return sysDictDataService.findByIds(ids);
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
    @Log(title = "字典数据表", businessType = BusinessType.INSERT)
    public R add(SysDictData recode) {
        return sysDictDataService.add(recode);
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
    @Log(title = "字典数据表", businessType = BusinessType.UPDATE)
    public R upd(SysDictData recode) {
        return sysDictDataService.updById(recode);
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
    @Log(title = "字典数据表", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return sysDictDataService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "字典数据表", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return sysDictDataService.deletes(ids);
    }


    /**
     * 根据字典类型 查询字典信息
     *
     * @param dictType 类型名称
     * @return 数据
     */
    @GetMapping(value = "/type/{dictType}")
    public R dictType(@PathVariable String dictType) {
        return selectDictDataByType.selectDictDataByType(dictType);
    }
}
