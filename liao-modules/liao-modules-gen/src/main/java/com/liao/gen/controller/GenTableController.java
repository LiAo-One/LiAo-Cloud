package com.liao.gen.controller;

import com.liao.datascope.annotation.Log;
import com.liao.common.core.R;
import com.liao.datascope.core.page.TableDataInfo;
import com.liao.common.core.text.Convert;
import com.liao.common.enums.BusinessType;
import com.liao.gen.dao.GenTableMapper;
import com.liao.gen.entity.GenTable;
import com.liao.gen.entity.GenTableColumn;
import com.liao.gen.services.GenTableColumnService;
import com.liao.gen.services.GenTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务 前端控制器
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
@RestController
@RequestMapping("/gen-table")
@Api(tags = {"代码生成业务"})
public class GenTableController {

    @Autowired
    private GenTableService genTableService;

    @Autowired
    private GenTableColumnService genTableColumnService;

    @Autowired
    private GenTableMapper genTableMapper;

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(GenTable recode) {
        return genTableService.selPage(recode);
    }

    /**
     * 查询数据库列表
     *
     * @param genTable 条件
     * @return 结果
     */
    @GetMapping("/db/list")
    @ApiOperation("查询数据库列表")
    public R selectDbTableList(GenTable genTable) {
        return genTableService.selectDbTableList(genTable);
    }

    /**
     * 导入表结构
     *
     * @param tables 条件
     * @return 结果
     */
    @PostMapping("/importTable")
    @ApiOperation("查询数据库列表")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    public R importTableSave(String tables) {
        List<GenTable> genTables = genTableService.selectDbTableList(Convert.toStrArray(tables));
        genTableService.importGenTable(genTables);
        return R.success();
    }

    /**
     * 修改代码生成业务
     *
     * @param talbleId 业务id
     * @return 结果集
     */
    @GetMapping(value = "/{talbleId}")
    public R getInfo(@PathVariable Long talbleId) {
        // 业务表数据
        GenTable table = genTableService.selectGenTableById(talbleId);
        // 业务字段结合
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        return R.success(map);
    }

    /**
     * 修改保存代码生成业务
     *
     * @param genTable 配置参数
     * @return 结果
     */
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public R editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.updateGenTable(genTable);
        return R.success();
    }

    /**
     * 查询数据字段列表
     *
     * @param tableId id
     * @return 结果
     */
    @GetMapping(value = "/column/{talbleId}")
    public TableDataInfo columnList(Long tableId) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
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
        return genTableService.findById(id);
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
        return genTableService.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @ApiOperation("添加数据")
    @Log(title = "代码生成业务", businessType = BusinessType.INSERT)
    public R add(GenTable recode) {
        return genTableService.add(recode);
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @PostMapping("upd_id")
    @ApiOperation("根据id修改")
    @Log(title = "代码生成业务", businessType = BusinessType.UPDATE)
    public R upd(GenTable recode) {
        return genTableService.updById(recode);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("del_id")
    @ApiOperation("根据id删除")
    @Log(title = "代码生成业务", businessType = BusinessType.DELETE)
    public R delete(Long id) {
        return genTableService.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "代码生成业务", businessType = BusinessType.DELETE)
    public R deletes(@RequestParam("ids") List<Long> ids) {
        return genTableService.deletes(ids);
    }


    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"liao_codes.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
