package com.liao.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.datascope.system.entity.SysLogininfor;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2020-12-30
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    @Update("truncate table sys_logininfor")
    void cleanSysLogininfor();

}
