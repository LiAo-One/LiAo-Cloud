package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.system.dao.SysRoleMenuMapper;
import com.liao.system.entity.SysRoleMenu;
import com.liao.system.services.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysRoleMenu recode) {
        // 分页信息
        IPage<SysRoleMenu> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();

        // 角色idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleId()), "role_id", recode.getRoleId());
        // 菜单idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getMenuId()), "menu_id", recode.getMenuId());

        // 排序信息
        QueryWrapper<SysRoleMenu> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysRoleMenuMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id){
        return R.r(sysRoleMenuMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids){
        return R.r(sysRoleMenuMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysRoleMenu recode) {
        return R.r(sysRoleMenuMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysRoleMenu recode) {
        if (StringUtils.isEmpty(recode.getRoleId())) {
            throw new MissingParametersException("角色菜单关联表ID");
        }
        return R.r(sysRoleMenuMapper.updateById(recode));
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
            throw new MissingParametersException("角色菜单关联表ID");
        }
        return R.r(sysRoleMenuMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids){
        return R.r(sysRoleMenuMapper.deleteBatchIds(ids));
    }
}
