package com.liao.gen.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.CustomException;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.gen.dao.GenTableColumnMapper;
import com.liao.gen.dao.GenTableMapper;
import com.liao.gen.entity.GenTable;
import com.liao.gen.entity.GenTableColumn;
import com.liao.gen.services.GenTableColumnService;
import com.liao.gen.services.GenTableService;
import com.liao.gen.util.GenUtils;
import com.liao.gen.util.VelocityInitializer;
import com.liao.gen.util.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成业务 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Autowired
    private GenTableMapper genTableMapper;

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(GenTable recode) {
        // 分页信息
        IPage<GenTable> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();

        // 编号
        queryWrapper.eq(StringUtils.isNotNull(recode.getTableId()), "table_id", recode.getTableId());
        // 表名称
        queryWrapper.eq(StringUtils.isNotNull(recode.getTableName()), "table_name", recode.getTableName());
        // 表描述
        queryWrapper.eq(StringUtils.isNotNull(recode.getTableComment()), "table_comment", recode.getTableComment());
        // 实体类名称
        queryWrapper.eq(StringUtils.isNotNull(recode.getClassName()), "class_name", recode.getClassName());
        // 生成包路径
        queryWrapper.eq(StringUtils.isNotNull(recode.getPackageName()), "package_name", recode.getPackageName());
        // 生成模块名
        queryWrapper.eq(StringUtils.isNotNull(recode.getModuleName()), "module_name", recode.getModuleName());
        // 生成业务名
        queryWrapper.eq(StringUtils.isNotNull(recode.getBusinessName()), "business_name", recode.getBusinessName());
        // 生成功能名
        queryWrapper.eq(StringUtils.isNotNull(recode.getFunctionName()), "function_name", recode.getFunctionName());
        // 生成功能作者
        queryWrapper.eq(StringUtils.isNotNull(recode.getFunctionAuthor()), "function_author", recode.getFunctionAuthor());
        // 父级菜单id
        queryWrapper.eq(StringUtils.isNotNull(recode.getParentMenuId()), "parent_menu_id", recode.getParentMenuId());
        // 创建时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 更新时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<GenTable> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(genTableMapper.selectPage(page, wrapper));
    }

    /**
     * 查询数据表列表
     *
     * @param genTable 查询参数
     * @return 结果
     */
    @Override
    public R selectDbTableList(GenTable genTable) {

        // 分页信息
        Page page = (Page) PageUtils.startPage();

        return R.r(genTableMapper.selectDbTableList(page, genTable));
    }

    /**
     * 查询 指定名称表结构
     *
     * @param tableNames 表集合
     * @return 集合
     */
    @Override
    public List<GenTable> selectDbTableList(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    /**
     * 导入表结构
     *
     * @param tableList 集合
     */
    @Override
    @Transactional
    public void importGenTable(List<GenTable> tableList) {
        try {
            for (GenTable table : tableList) {

                // 获取表名
                String tableName = table.getTableName();

                // 格式化表参数
                GenUtils.initTable(table);

                // 判断结果
                int insert = genTableMapper.insert(table);
                if (insert > 0) {
                    // 保存列的信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);

                    for (GenTableColumn column : genTableColumns) {
                        GenUtils.initColumnField(column, table);
                        genTableColumnMapper.insert(column);
                    }
                }
            }
        } catch (Exception e) {
            throw new CustomException("导入失败：" + e.getMessage());
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        return R.r(genTableMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(genTableMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(GenTable recode) {
        return R.r(genTableMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(GenTable recode) {
        if (StringUtils.isEmpty(recode.getTableId())) {
            throw new MissingParametersException("代码生成业务ID");
        }
        return R.r(genTableMapper.updateById(recode));
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
            throw new MissingParametersException("代码生成业务ID");
        }
        return R.r(genTableMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        List<GenTable> genTables = genTableMapper.selectBatchIds(ids);
        for (GenTable genTable : genTables) {
            genTableColumnMapper.deleteGenTableColumnByTableId(genTable.getTableId());
        }
        return R.r(genTableMapper.deleteBatchIds(ids));
    }

    /**
     * 查询业务信息
     *
     * @param id 业务id
     * @return 结果
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        return genTableMapper.selectById(id);
    }

    /**
     * 修改业务参数
     *
     * @param genTable 参数
     */
    @Override
    public void updateGenTable(GenTable genTable) {
        int row = genTableMapper.updateById(genTable);
        if (row > 0) {
            // 批量更新 字段表数据
            genTableColumnService.updateBatchById(genTable.getColumns());

        }
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String[] tableNames) {

        // 字节缓冲区
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 压缩包对象
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 查询表信息
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    /**
     * 查询表信息 并生成代码
     *
     * @param tableName 表名称
     * @param zip       压缩包操作流
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        GenTable table = genTableMapper.selectGenTableByName(tableName);
        // 设置主键列信息
        setPkColumn(table);

        // 初始化 vm 方法
        VelocityInitializer.initVelocity();

        // 加载模板参数
        VelocityContext context = VelocityUtils.prepareContext(table);

        // 加载模板路径
        List<String> templates = VelocityUtils.getTemplateList();

        // 循环渲染模板
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            // 加载模板
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);

            tpl.merge(context, sw);
            try {
                // 加载文件路径
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                // 写入
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {

        // 遍历设置主表信息
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }

        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
    }

}
