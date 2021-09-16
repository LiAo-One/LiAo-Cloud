package com.liao.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 管理员角色关联表
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysAdminRole对象", description="管理员角色关联表")
public class SysAdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "admin_id", type = IdType.INPUT)
    private Long adminId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
