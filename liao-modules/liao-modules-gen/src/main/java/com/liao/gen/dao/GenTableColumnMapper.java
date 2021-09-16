package com.liao.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.gen.entity.GenTableColumn;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {


    /**
     * 根据表名查询列的信息
     *
     * @param tableName 表名称
     * @return 列信息集合
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    /**
     * 删除字段数据
     *
     * @param tableId 业务id
     * @return 结果
     */
    int deleteGenTableColumnByTableId(Long tableId);
}
