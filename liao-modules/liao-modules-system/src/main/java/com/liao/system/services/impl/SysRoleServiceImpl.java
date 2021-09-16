package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.utils.StringUtils;
import com.liao.system.dao.SysRoleMapper;
import com.liao.system.entity.SysRole;
import com.liao.system.entity.SysRoleMenu;
import com.liao.system.services.SysRoleMenuService;
import com.liao.system.services.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysRole recode) {
        // 分页信息
        IPage<SysRole> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();

        // 角色idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleId()), "role_id", recode.getRoleId());
        // 角色名称SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleName()), "role_name", recode.getRoleName());
        // 权限字符SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleAuthority()), "role_authority", recode.getRoleAuthority());
        // 顺序SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleSort()), "role_sort", recode.getRoleSort());
        // 角色状态SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getRoleStatus()), "role_status", recode.getRoleStatus());
        // 创建时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 修改时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<SysRole> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysRoleMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        if (StringUtils.isEmpty(id)) {
            return R.r(sysRoleMapper.selectList(null));
        }
        return R.r(sysRoleMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysRoleMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysRole recode, List<Long> menuIds) {
        sysRoleMapper.insert(recode);

        if (StringUtils.isNotEmpty(menuIds)) {
            // 批量添加
            sysRoleMenuService.saveBatch(buildSysRoleMenuList(recode.getRoleId(), menuIds));
        }

        return R.success();
    }

    /**
     * 根据id修改
     *
     * @param recode  修改参数
     * @param menuIds 按钮id
     * @return 结果
     */
    @Override
    public R updById(SysRole recode, List<Long> menuIds) {
        if (StringUtils.isEmpty(recode.getRoleId())) {
            throw new MissingParametersException("角色表ID");
        }

        if (StringUtils.isNotEmpty(menuIds)) {
            // 清空原有角色按
            sysRoleMenuService.removeById(recode.getRoleId());

            // 批量添加
            sysRoleMenuService.saveBatch(buildSysRoleMenuList(recode.getRoleId(), menuIds));
        }

        return R.r(sysRoleMapper.updateById(recode));
    }

    /**
     * 构建批量操作按钮集合
     *
     * @param roleId  角色id
     * @param menuIds 按钮Id
     * @return 按钮集合
     */
    public List<SysRoleMenu> buildSysRoleMenuList(Long roleId, List<Long> menuIds) {
        // 按钮数据
        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();

        for (Long menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();

            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);

            sysRoleMenus.add(sysRoleMenu);
        }

        return sysRoleMenus;
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
            throw new MissingParametersException("角色表ID");
        }
        return R.r(sysRoleMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysRoleMapper.deleteBatchIds(ids));
    }
}
