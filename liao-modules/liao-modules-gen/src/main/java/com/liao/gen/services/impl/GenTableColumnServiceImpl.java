package com.liao.gen.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.gen.dao.GenTableColumnMapper;
import com.liao.gen.entity.GenTableColumn;
import com.liao.gen.services.GenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnService {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(GenTableColumn recode) {
        // 分页信息
        IPage<GenTableColumn> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<GenTableColumn> queryWrapper = new QueryWrapper<>();

        // 编号
        queryWrapper.eq(StringUtils.isNotNull(recode.getColumnId()), "column_id", recode.getColumnId());
        // 归属表编号
        queryWrapper.eq(StringUtils.isNotNull(recode.getTableId()), "table_id", recode.getTableId());
        // 列名称
        queryWrapper.eq(StringUtils.isNotNull(recode.getColumnName()), "column_name", recode.getColumnName());
        // 列描述
        queryWrapper.eq(StringUtils.isNotNull(recode.getColumnComment()), "column_comment", recode.getColumnComment());
        // 列类型
        queryWrapper.eq(StringUtils.isNotNull(recode.getColumnType()), "column_type", recode.getColumnType());
        // JAVA类型
        queryWrapper.eq(StringUtils.isNotNull(recode.getJavaType()), "java_type", recode.getJavaType());
        // JAVA字段名
        queryWrapper.eq(StringUtils.isNotNull(recode.getJavaField()), "java_field", recode.getJavaField());
        // 是否主键（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsPk()), "is_pk", recode.getIsPk());
        // 是否自增（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsIncrement()), "is_increment", recode.getIsIncrement());
        // 是否必填（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsRequired()), "is_required", recode.getIsRequired());
        // 是否为插入字段（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsInsert()), "is_insert", recode.getIsInsert());
        // 是否编辑字段（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsEdit()), "is_edit", recode.getIsEdit());
        // 是否列表字段（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsList()), "is_list", recode.getIsList());
        // 是否查询字段（1是）
        queryWrapper.eq(StringUtils.isNotNull(recode.getIsQuery()), "is_query", recode.getIsQuery());
        // 查询方式（等于、不等于、大于、小于、范围）
        queryWrapper.eq(StringUtils.isNotNull(recode.getQueryType()), "query_type", recode.getQueryType());
        // 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
        queryWrapper.eq(StringUtils.isNotNull(recode.getHtmlType()), "html_type", recode.getHtmlType());
        // 字典类型
        queryWrapper.eq(StringUtils.isNotNull(recode.getDictType()), "dict_type", recode.getDictType());
        // 排序
        queryWrapper.eq(StringUtils.isNotNull(recode.getSort()), "sort", recode.getSort());
        // 创建时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 更新时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<GenTableColumn> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(genTableColumnMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id){
        return R.r(genTableColumnMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids){
        return R.r(genTableColumnMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(GenTableColumn recode) {
        return R.r(genTableColumnMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(GenTableColumn recode) {
        if (StringUtils.isEmpty(recode.getColumnId())) {
            throw new MissingParametersException("代码生成业务表字段ID");
        }
        return R.r(genTableColumnMapper.updateById(recode));
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R delete(Long id){
        if (StringUtils.isEmpty(id)) {
            throw new MissingParametersException("代码生成业务表字段ID");
        }
        return R.r(genTableColumnMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids){
        return R.r(genTableColumnMapper.deleteBatchIds(ids));
    }

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }
}
