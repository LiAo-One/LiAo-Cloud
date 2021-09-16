package com.liao.system.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.system.entity.SysDictType;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author LiAo
 * @since 2021-06-01
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(SysDictType recode);

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
    R add(SysDictType recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(SysDictType recode);

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
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 类型主键
     * @return 结果
     */
    R selectDictDataByType(String dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    R selectDictTypeAll();
}
