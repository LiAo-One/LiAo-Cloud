package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.commons.core.R;
import com.liao.commons.core.page.PageUtils;
import com.liao.commons.exception.check.MissingParametersException;
import com.liao.commons.utils.StringUtils;
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    /**
     * 分页查询
     *
     * @param recode 条件
     * @return 结果
     */
    @Override
    public R selPage(${entity} recode) {
        // 分页信息
        IPage<${entity}> page = PageUtils.startPage();

        // 构造条件
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();

<#list table.fields as field>
    <#if field.name!= "version" && field.name != "deleted">
        // ${field.comment}
        queryWrapper.eq(StringUtils.isNotNull(recode.get${field.capitalName}()), "${field.name}", recode.get${field.capitalName}());
    </#if>
</#list>

        // 排序信息
        QueryWrapper<${entity}> wrapper = PageUtils.startOrderBy(queryWrapper);

        // 返回结果
        return R.r(${table.mapperName?uncap_first}.selectPage(page, wrapper));
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @Override
<#list table.fields as field>
    <#if field_index == 0>
    public R findById(${field.propertyType} id){
    </#if>
</#list>
        return R.r(${table.mapperName?uncap_first}.selectById(id));
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @Override
<#list table.fields as field>
    <#if field_index == 0>
    public R findByIds(List<${field.propertyType}> ids){
    </#if>
</#list>
        return R.r(${table.mapperName?uncap_first}.selectBatchIds(ids));
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @Override
    public R add(${entity} recode) {
        return R.r(${table.mapperName?uncap_first}.insert(recode));
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @Override
    public R updById(${entity} recode) {
<#list table.fields as field>
    <#if field_index == 0>
        if (StringUtils.isEmpty(recode.get${field.capitalName}())) {
    </#if>
</#list>
            throw new MissingParametersException("${table.comment!}ID");
        }
        return R.r(${table.mapperName?uncap_first}.updateById(recode));
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @Override
<#list table.fields as field>
    <#if field_index == 0>
    public R delete(${field.propertyType} id){
    </#if>
</#list>
        if (StringUtils.isEmpty(id)) {
            throw new MissingParametersException("${table.comment!}ID");
        }
        return R.r(${table.mapperName?uncap_first}.deleteById(id));
    }

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
<#list table.fields as field>
    <#if field_index == 0>
    public R deletes(List<${field.propertyType}> ids){
    </#if>
</#list>
        return R.r(${table.mapperName?uncap_first}.deleteBatchIds(ids));
    }
}
</#if>
