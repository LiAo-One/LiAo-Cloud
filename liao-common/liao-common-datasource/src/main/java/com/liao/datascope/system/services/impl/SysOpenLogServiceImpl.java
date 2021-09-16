package com.liao.datascope.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.datascope.system.dao.SysOpenLogMapper;
import com.liao.datascope.system.entity.SysOpenLog;
import com.liao.datascope.system.services.SysOpenLogService;
import com.liao.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Service
public class SysOpenLogServiceImpl extends ServiceImpl<SysOpenLogMapper, SysOpenLog> implements SysOpenLogService {

    @Autowired
    private SysOpenLogMapper sysOpenLogMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysOpenLog recode) {
        // 分页信息
        IPage<SysOpenLog> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysOpenLog> queryWrapper = getQueryWrapper(recode);

        // 排序信息
        QueryWrapper<SysOpenLog> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysOpenLogMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        return R.r(sysOpenLogMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysOpenLogMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysOpenLog recode) {
        return R.r(sysOpenLogMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysOpenLog recode) {
        if (StringUtils.isEmpty(recode.getOperId())) {
            throw new MissingParametersException("操作日志记录ID");
        }
        return R.r(sysOpenLogMapper.updateById(recode));
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
            throw new MissingParametersException("操作日志记录ID");
        }
        return R.r(sysOpenLogMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysOpenLogMapper.deleteBatchIds(ids));
    }

    /**
     * 清空操作日志
     *
     * @return 结果
     */
    @Override
    public R clean() {
        sysOpenLogMapper.cleanSysOpenLog();
        // 返回结果
        return R.success();
    }

    /**
     * 构建日志 SQL操作条件
     *
     * @param recode 日志信息
     * @return QueryWrapper
     */
    private QueryWrapper<SysOpenLog> getQueryWrapper(SysOpenLog recode) {
        // 构造条件
        QueryWrapper<SysOpenLog> queryWrapper = new QueryWrapper<>();

        // 日志idSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOperId()), "oper_id", recode.getOperId());
        // 模块标题SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOperTitle()), "oper_title", recode.getOperTitle());
        // 业务类型（0其它 1新增 2修改 3删除）SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenBusinessType()), "open_business_type", recode.getOpenBusinessType());
        // 方法名称SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenMethod()), "open_method", recode.getOpenMethod());
        // 请求方式SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenRequestMethod()), "open_request_method", recode.getOpenRequestMethod());
        // 主机地址IPSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOperIp()), "oper_ip", recode.getOperIp());
        // 请求urlSysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenUrl()), "open_url", recode.getOpenUrl());
        // 地址SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOperLocation()), "oper_location", recode.getOperLocation());
        // 请求参数SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOperParam()), "oper_param", recode.getOperParam());
        // 返回参数SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenResult()), "open_result", recode.getOpenResult());
        // 状态SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenStatus()), "open_status", recode.getOpenStatus());
        // 错误消息SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getOpenErrorMsg()), "open_error_msg", recode.getOpenErrorMsg());
        // 创建时间SysOpenLogServiceImpl.java
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());

        // 排序信息
        return queryWrapper;
    }
}
