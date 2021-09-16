package com.liao.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liao.gen.entity.GenTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 代码生成业务 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
public interface GenTableMapper extends BaseMapper<GenTable> {

    /**
     * 查询数据表列表
     *
     * @param genTable 查询参数
     * @return 结果
     */
    IPage<GenTable> selectDbTableList(@Param("page") Page<?> page, @Param("genTable") GenTable genTable);


    /**
     * 查询 指定名称表结构
     *
     * @param tableNames 表集合
     * @return 集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息 一对多
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);
}
