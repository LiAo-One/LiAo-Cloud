package com.liao.datascope.system.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.datascope.system.entity.SysOpenLog;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
public interface SysOpenLogService extends IService<SysOpenLog> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(SysOpenLog recode);

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
    R add(SysOpenLog recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(SysOpenLog recode);

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
     * 清空操作日志
     *
     * @return 结果
     */
    R clean();
}
