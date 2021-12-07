package com.liao.system.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.common.core.R;
import com.liao.system.entity.SysAdmin;

import java.util.List;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-14
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 管理员登录
     *
     * @param adminAccount  账号
     * @param adminPassword 密码
     * @return 结果
     */
    String login(String adminAccount, String adminPassword);

    /**
     * 获取当前登录用户数据
     *
     * @return 用户信息
     */
    SysAdmin getLoginInfo();

    /**
     * 退出登录
     *
     * @param token token
     * @return 结果
     */
    R logout(String token);

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    R selPage(SysAdmin recode);

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
    R add(SysAdmin recode, Long roleId);

    /**
     * 根据id修改用户
     *
     * @param recode 修改参数
     * @param roleId 角色id
     * @return 修改结果
     */
    R updById(SysAdmin recode, Long roleId);

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
}
