package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.system.dao.SysAdminRoleMapper;
import com.liao.system.entity.SysAdminRole;
import com.liao.system.services.SysAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员角色关联表 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Service
public class SysAdminRoleServiceImpl extends ServiceImpl<SysAdminRoleMapper, SysAdminRole> implements SysAdminRoleService {

    @Autowired
    private SysAdminRoleMapper sysAdminRoleMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    @CachePut(key = "#recode.adminId")
    public R selPage(SysAdminRole recode) {
        // 分页信息
        IPage<SysAdminRole> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysAdminRole> queryWrapper = new QueryWrapper<>();

        // 用户idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminId()), "admin_id", recode.getAdminId());
        // 角色idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleId()), "role_id", recode.getRoleId());

        // 排序信息
        QueryWrapper<SysAdminRole> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysAdminRoleMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        return R.r(sysAdminRoleMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysAdminRoleMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysAdminRole recode) {
        return R.r(sysAdminRoleMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysAdminRole recode) {
        if (StringUtils.isEmpty(recode.getAdminId())) {
            throw new MissingParametersException("管理员角色关联表ID");
        }
        return R.r(sysAdminRoleMapper.updateById(recode));
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
            throw new MissingParametersException("管理员角色关联表ID");
        }
        return R.r(sysAdminRoleMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysAdminRoleMapper.deleteBatchIds(ids));
    }
}
