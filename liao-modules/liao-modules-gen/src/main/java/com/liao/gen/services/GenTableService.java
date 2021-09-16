package com.liao.gen.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.gen.entity.GenTable;

import java.util.List;

/**
 * <p>
 * 代码生成业务 服务类
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
public interface GenTableService extends IService<GenTable> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(GenTable recode);

    /**
     * 查询数据表列表
     *
     * @param genTable 查询参数
     * @return 结果
     */
    R selectDbTableList(GenTable genTable);

    /**
     * 查询 指定名称表结构
     *
     * @param tableNames 表集合
     * @return 集合
     */
    List<GenTable> selectDbTableList(String[] tableNames);

    /**
     * 导入表结构
     *
     * @param tableList 集合
     */
    void importGenTable(List<GenTable> tableList);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    R findById(Long id);

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    R findByIds(List<Long> ids);

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    R add(GenTable recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(GenTable recode);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    R delete(Long id);

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    R deletes(List<Long> ids);

    /**
     * 查询业务信息
     *
     * @param id 业务id
     * @return 结果
     */
    GenTable selectGenTableById(Long id);


    /**
     * 修改业务参数
     *
     * @param genTable 参数
     */
    void updateGenTable(GenTable genTable);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] downloadCode(String[] tableNames);
}
