package ${package.Service};

import com.liao.commons.core.R;
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
     R selPage(${entity} recode);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
 <#list table.fields as field>
  <#if field_index == 0>
     R findById(${field.propertyType} id);
  </#if>
 </#list>

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
 <#list table.fields as field>
   <#if field_index == 0>
     R findByIds(List<${field.propertyType}> ids);
   </#if>
 </#list>

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
     R add(${entity} recode);

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
     R updById(${entity} recode);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
 <#list table.fields as field>
  <#if field_index == 0>
     R delete(${field.propertyType} id);
  </#if>
 </#list>

    /**
     * 根据id批量删除
     *
     * @param ids id集合
     * @return 结果
     */
 <#list table.fields as field>
  <#if field_index == 0>
     R deletes(List<${field.propertyType}> ids);
  </#if>
 </#list>
}
</#if>
