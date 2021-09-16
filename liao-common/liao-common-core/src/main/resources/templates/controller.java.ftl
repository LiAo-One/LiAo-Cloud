package ${package.Controller};

import com.liao.commons.annotation.Log;
import com.liao.commons.core.R;
import com.liao.commons.enums.BusinessType;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

<#--<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>-->
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(tags = {"${table.comment!}"})
<#if kotlin>class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if><#else><#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{<#else>public class ${table.controllerName} {</#if>

<#--    private Logger log = LoggerFactory.getLogger(getClass());-->
    @Autowired
<#--    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};-->
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 分页查询 排序
     *
     * @param recode 条件
     * @return 结果
     */
    @PostMapping("sel_page")
    @ApiOperation("分页、排序、动态条件")
    public R selPage(${entity} recode) {
        return ${table.serviceName?uncap_first}.selPage(recode);
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("sel_id")
    @ApiOperation("根据id查询数据")
    <#list table.fields as field>
        <#if field_index == 0>
    public R findById(${field.propertyType} id){
        </#if>
    </#list>
        return ${table.serviceName?uncap_first}.findById(id);
    }

    /**
     * 根据ids查询数据
     *
     * @param ids ids
     * @return 结果
     */
    @PostMapping("sel_ids")
    @ApiOperation("根据ids批量查询")
    <#list table.fields as field>
        <#if field_index == 0>
    public R findByIds(@RequestParam("ids") List<${field.propertyType}> ids){
        </#if>
    </#list>
        return ${table.serviceName?uncap_first}.findByIds(ids);
    }

    /**
     * 添加数据
     *
     * @param recode 添加参数
     * @return 结果
     */
    @PostMapping("add")
    @ApiOperation("添加数据")
    @Log(title = "${table.comment!}",businessType = BusinessType.INSERT)
    public R add(${entity} recode) {
        return ${table.serviceName?uncap_first}.add(recode);
    }

    /**
     * 根据id修改
     *
     * @param recode 修改参数
     * @return 结果
     */
    @PostMapping("upd_id")
    @ApiOperation("根据id修改")
    @Log(title = "${table.comment!}",businessType = BusinessType.UPDATE)
    public R upd(${entity} recode) {
        return ${table.serviceName?uncap_first}.updById(recode);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("del_id")
    @ApiOperation("根据id删除")
    @Log(title = "${table.comment!}", businessType = BusinessType.DELETE)
    <#list table.fields as field>
        <#if field_index == 0>
    public R delete(${field.propertyType} id){
        </#if>
    </#list>
        return ${table.serviceName?uncap_first}.delete(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("del_ids")
    @ApiOperation("根据ids批量删除")
    @Log(title = "${table.comment!}", businessType = BusinessType.DELETE)
    <#list table.fields as field>
        <#if field_index == 0>
    public R deletes(@RequestParam("ids") List<${field.propertyType}> ids){
        </#if>
    </#list>
        return ${table.serviceName?uncap_first}.deletes(ids);
    }
}
</#if>