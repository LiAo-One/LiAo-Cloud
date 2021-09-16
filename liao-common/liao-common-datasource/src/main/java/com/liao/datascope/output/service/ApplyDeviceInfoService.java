package com.liao.datascope.output.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.datascope.output.entity.ApplyDeviceInfo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 同步任务查询记录表 服务类
 * </p>
 *
 * @author LiAo
 * @since 2021-05-13
 */
public interface ApplyDeviceInfoService extends IService<ApplyDeviceInfo> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(ApplyDeviceInfo recode);

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
    R add(ApplyDeviceInfo recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    R updById(ApplyDeviceInfo recode);

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
     * 分页获取设备名称列表
     *
     * @param numb          页码
     * @param size          长度
     * @param synchronousId 条件
     * @return 结果
     */
    Set<String> getApplyDeviceInfoSet(int numb, int size, Long synchronousId);

}
