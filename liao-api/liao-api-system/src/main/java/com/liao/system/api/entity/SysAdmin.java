package com.liao.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysAdmin对象", description="管理员")
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员主键")
    @TableId(value = "admin_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long adminId;

    @ApiModelProperty(value = "账户")
    private String adminAccount;

    @ApiModelProperty(value = "密码")
    private String adminPassword;

    @ApiModelProperty(value = "姓名")
    private String adminName;

    @ApiModelProperty(value = "性别")
    private String adminSex;

    @ApiModelProperty(value = "头像连接")
    private String adminAvatar;

    @ApiModelProperty(value = "邮箱")
    private String adminEmail;

    @ApiModelProperty(value = "昵称")
    private String adminNickname;

    @ApiModelProperty(value = "备注")
    private String adminRemarks;

    @ApiModelProperty(value = "乐观锁")
    @TableField(select = false)
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(select = false)
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
