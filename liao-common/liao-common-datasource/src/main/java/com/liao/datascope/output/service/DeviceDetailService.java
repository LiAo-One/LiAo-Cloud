package com.liao.datascope.output.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.datascope.output.entity.DeviceDetail;

import java.util.List;

/**
 * <p>
 * 设备详细服务类
 * </p>
 *
 * @author LiAo
 * @since 2021-05-14
 */
public interface DeviceDetailService extends IService<DeviceDetail> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(DeviceDetail recode);

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
    R add(DeviceDetail recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(DeviceDetail recode);

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
     * 条件查询全部数据 用于数据导出
     *
     * @param recode 条件
     * @return 结果
     */
    List<DeviceDetail> selectDeviceDetailList(DeviceDetail recode);

    /**
     * 批量删除设备
     *
     * @param ids id
     * @return 结果
     */
    String deleteDevices(List<Long> ids);
}
