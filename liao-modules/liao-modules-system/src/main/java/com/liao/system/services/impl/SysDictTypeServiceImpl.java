package com.liao.system.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.common.core.R;
import com.liao.datascope.core.page.PageUtils;
import com.liao.common.exception.check.MissingParametersException;
import com.liao.datascope.utils.DictUtils;
import com.liao.common.utils.StringUtils;
import com.liao.system.api.entity.SysDictData;
import com.liao.system.dao.SysDictDataMapper;
import com.liao.system.dao.SysDictTypeMapper;
import com.liao.system.entity.SysDictType;
import com.liao.system.services.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author LiAo
 * @since 2021-06-01
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;


    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(SysDictType recode) {
        // 分页信息
        IPage<SysDictType> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();

        // 字典主键
        queryWrapper.eq(StringUtils.isNotNull(recode.getDictId()), "dict_id", recode.getDictId());
        // 字典名称
        queryWrapper.eq(StringUtils.isNotNull(recode.getDictName()), "dict_name", recode.getDictName());
        // 字典类型
        queryWrapper.eq(StringUtils.isNotNull(recode.getDictType()), "dict_type", recode.getDictType());
        // 创建时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getCreateTime()), "create_time", recode.getCreateTime());
        // 更新时间
        queryWrapper.eq(StringUtils.isNotNull(recode.getUpdateTime()), "update_time", recode.getUpdateTime());

        // 排序信息
        QueryWrapper<SysDictType> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(sysDictTypeMapper.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
    public R findById(Long id) {
        return R.r(sysDictTypeMapper.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
    public R findByIds(List<Long> ids) {
        return R.r(sysDictTypeMapper.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(SysDictType recode) {
        return R.r(sysDictTypeMapper.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(SysDictType recode) {
        if (StringUtils.isEmpty(recode.getDictId())) {
            throw new MissingParametersException("字典类型表ID");
        }
        return R.r(sysDictTypeMapper.updateById(recode));
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
            throw new MissingParametersException("字典类型表ID");
        }
        return R.r(sysDictTypeMapper.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public R deletes(List<Long> ids) {
        return R.r(sysDictTypeMapper.deleteBatchIds(ids));
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 类型主键
     * @return 结果
     */
    @Override
    public R selectDictDataByType(String dictType) {

        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);

        if (StringUtils.isNotEmpty(dictDatas)) {
            return R.r(dictDatas);
        }

        dictDatas = sysDictDataMapper.selectDictDataByType(dictType);

        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return R.r(dictDatas);
        }
        return null;
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public R selectDictTypeAll() {
        return R.success(sysDictTypeMapper.selectList(null));
    }
}
