package com.liao.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liao.system.api.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author LiAo
 * @since 2021-06-01
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(@Param("dictType") String dictType);

}
