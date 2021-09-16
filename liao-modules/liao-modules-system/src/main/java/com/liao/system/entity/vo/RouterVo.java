package com.liao.system.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 路由配置信息
 * </p>
 *
 * @author LiAo
 * @since 2020/12/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "路由配置信息", description = "路由配置信息")
public class RouterVo {

    @ApiModelProperty(value = "路由名字")
    private String name;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "是否隐藏路由")
    private boolean hidden;

    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    @ApiModelProperty(value = "组件地址")
    private String component;

    @ApiModelProperty(value = "是否嵌套")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "其他元素")
    private MetaVo meta;

    @ApiModelProperty(value = "子路由")
    private List<RouterVo> children;

}
