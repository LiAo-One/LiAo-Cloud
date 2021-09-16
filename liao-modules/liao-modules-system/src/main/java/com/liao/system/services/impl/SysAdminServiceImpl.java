package com.liao.system.services.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.constant.Constants;
import com.liao.common.core.R;
import com.liao.common.exception.BusinessException;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.common.exception.user.LoginException;
import com.liao.common.exception.user.PermissionException;
import com.liao.common.utils.StringUtils;
import com.liao.common.utils.TokenUtil;
import com.liao.datascope.core.page.PageUtils;
import com.liao.datascope.system.entity.SysMenu;
import com.liao.cache.utils.RedisUtil;
import com.liao.framework.manager.AsyncManager;
import com.liao.framework.manager.factory.AsyncFactory;
import com.liao.system.dao.SysAdminMapper;
import com.liao.system.dao.SysAdminRoleMapper;
import com.liao.system.dao.SysRoleMapper;
import com.liao.system.entity.SysAdmin;
import com.liao.system.entity.SysAdminRole;
import com.liao.system.entity.SysRole;
import com.liao.system.entity.vo.RouterVo;
import com.liao.system.services.SysAdminRoleService;
import com.liao.system.services.SysAdminService;
import com.liao.system.services.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-14
 */
@Service
@CacheConfig(cacheNames = "sys_admin")
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    // 管理员
    @Autowired
    private SysAdminMapper sysAdminMapper;

    // Redis 操作
    @Autowired
    private RedisUtil redisUtil;

    // 角色与菜单
    @Autowired
    private SysAdminRoleMapper sysAdminRoleMapper;

    // 角色
    @Autowired
    private SysRoleMapper sysRoleMapper;

    // 按钮
    @Autowired
    private SysMenuService sysMenuService;

    // 用户与角色
    @Autowired
    private SysAdminRoleService sysAdminRoleService;


    /**
     * 管理员登录
     *
     * @param adminAccount  账号
     * @param adminPassword 密码
     * @return 结果
     */
    @Override
    public R login(String adminAccount, String adminPassword) {

        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_account", adminAccount)
                .eq("admin_password", adminPassword);
        List<SysAdmin> sysAdminIPage = sysAdminMapper.selectPage(PageUtils.startDefPage(), wrapper).getRecords();

        // 登录校验
        if (StringUtils.isEmpty(sysAdminIPage)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(adminAccount, Constants.LOGIN_FAIL, "账号密码错误"));
            throw new LoginException();
        }

        Map<String, String> map = new HashMap<>();

        Long userId = sysAdminIPage.get(0).getAdminId();

        // userJsonStr
        String userJsonStr = JSON.toJSONString(sysAdminIPage.get(0));

        map.put("user", userJsonStr);

        // userRole
        SysRole sysRole = sysRoleMapper.selLoginUserRole(userId);

        if (StringUtils.isNull(sysRole)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(adminAccount, Constants.LOGIN_FAIL, "账户角色权限信息异常"));
            throw new PermissionException();
        }

        // userRole
        String userRole = JSON.toJSONString(sysRole);
        map.put("role", userRole);

        // userMenu
        List<SysMenu> menus = sysMenuService.selectLoginMenuList(sysRole.getRoleId());

        if (StringUtils.isNull(menus)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(adminAccount, Constants.LOGIN_FAIL, "账户角色权限信息异常"));
            throw new PermissionException();
        }

        // 转换为路由
        List<RouterVo> routerVos = sysMenuService.buildMenus(menus);

        // userMenu
        String userMenu = JSON.toJSONString(routerVos);
        map.put("menu", userMenu);

        // 生成Token
        String token = TokenUtil.token(map);

        // 生成唯一id
        String redisKey = IdUtil.simpleUUID();

        // 入库
        redisUtil.set(redisKey, token, Constants.EXPIRE_DATE);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(adminAccount, Constants.LOGIN_SUCCESS, "登录成功"));

        return R.success(redisKey, map);
    }

    /**
     * 退出登录
     *
     * @param token token
     * @return 结果
     */
    @Override
    public R logout(String token) {
        redisUtil.del(token);
        return R.success();
    }


    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysAdmin recode) {
        // 分页信息
        IPage<SysAdmin> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();

        // 管理员主键SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminId()), "admin_id", recode.getAdminId());
        // 账户SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminAccount()), "admin_account", recode.getAdminAccount());
        // 密码SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminPassword()), "admin_password", recode.getAdminPassword());
        // 姓名SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminName()), "admin_name", recode.getAdminName());
        // 性别SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminSex()), "admin_sex", recode.getAdminSex());
        // 头像连接SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminAvatar()), "admin_avatar", recode.getAdminAvatar());
        // 邮箱SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminEmail()), "admin_email", recode.getAdminEmail());
        // 昵称SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminNickname()), "admin_nickname", recode.getAdminNickname());
        // 备注SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getAdminRemarks()), "admin_remarks", recode.getAdminRemarks());
        // 创建时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 修改时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<SysAdmin> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysAdminMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {

        SysAdmin sysAdmin = sysAdminMapper.selectById(id);

        if (StringUtils.isNull(sysAdmin)) {
            throw new BusinessException("用户查询为空");
        }

        Map<String, Object> map = new HashMap<>();

        // userRole
        SysRole sysRole = sysRoleMapper.selLoginUserRole(sysAdmin.getAdminId());

        // userRole
        map.put("user", sysAdmin);
        map.put("role", sysRole);
        map.put("roles", sysRoleMapper.selectList(null));

        return R.success(map);
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysAdminMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public R add(SysAdmin recode, Long roleId) {

        sysAdminMapper.insert(recode);

        // 角色id不为空
        if (StringUtils.isNotEmpty(roleId)) {
            SysAdminRole sysAdminRole = new SysAdminRole();

            sysAdminRole.setAdminId(recode.getAdminId());
            sysAdminRole.setRoleId(roleId);

            sysAdminRoleService.add(sysAdminRole);
        }

        return R.success();
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public R updById(SysAdmin recode, Long roleId) {
        if (StringUtils.isEmpty(recode.getAdminId())) {
            throw new MissingParametersException("管理员ID");
        }
        // 角色信息更新
        if (StringUtils.isNotNull(roleId)) {
            SysAdminRole sysAdminRole = new SysAdminRole();
            sysAdminRole.setAdminId(recode.getAdminId());
            sysAdminRole.setRoleId(roleId);
            // 清除缓存
            sysAdminRoleMapper.deleteById(roleId);
            // 执行添加
            sysAdminRoleMapper.insert(sysAdminRole);
        }
        // 用户信息更新
        sysAdminMapper.updateById(recode);

        return R.success();
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
            throw new MissingParametersException("管理员ID");
        }
        return R.r(sysAdminMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysAdminMapper.deleteBatchIds(ids));
    }
}
