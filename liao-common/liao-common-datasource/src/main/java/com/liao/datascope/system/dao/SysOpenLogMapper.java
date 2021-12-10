package com.liao.datascope.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.system.api.entity.SysOpenLog;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2020-12-16
 */
public interface SysOpenLogMapper extends BaseMapper<SysOpenLog> {

    @Update("truncate table sys_open_log")
    void cleanSysOpenLog();

}
