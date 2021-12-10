package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.datascope.system.services.SysLogininforService;
import com.liao.common.utils.StringUtils;
import com.liao.system.api.entity.SysLogininfor;
import com.liao.system.dao.SysLogininforMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-30
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements SysLogininforService {

    @Autowired
    private SysLogininforMapper sysLogininforMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysLogininfor recode) {
        // 分页信息
        IPage<SysLogininfor> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysLogininfor> queryWrapper = new QueryWrapper<>();

        // 访问IDSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getInfoId()), "info_id", recode.getInfoId());
        // 用户账号SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getUserName()), "user_name", recode.getUserName());
        // 登录IP地址SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getIpaddr()), "ipaddr", recode.getIpaddr());
        // 登录地点SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getLoginLocation()), "login_location", recode.getLoginLocation());
        // 浏览器类型SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getBrowser()), "browser", recode.getBrowser());
        // 操作系统SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOs()), "os", recode.getOs());
        // 登录状态（0成功 1失败）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getStatus()), "status", recode.getStatus());
        // 提示消息SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getMsg()), "msg", recode.getMsg());
        // 访问时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "login_time", recode.getCreateTime());

        // 排序信息
        QueryWrapper<SysLogininfor> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysLogininforMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id){
        return R.r(sysLogininforMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids){
        return R.r(sysLogininforMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysLogininfor recode) {
        return R.r(sysLogininforMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysLogininfor recode) {
        if (StringUtils.isEmpty(recode.getInfoId())) {
            throw new MissingParametersException("系统访问记录ID");
        }
        return R.r(sysLogininforMapper.updateById(recode));
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
            throw new MissingParametersException("系统访问记录ID");
        }
        return R.r(sysLogininforMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids){
        return R.r(sysLogininforMapper.deleteBatchIds(ids));
    }

    /**
     * 清空操作日志
     *
     * @return 结果
     */
    @Override
    public R clean() {
        sysLogininforMapper.cleanSysLogininfor();
        // 返回结果
        return R.success();
    }
}
