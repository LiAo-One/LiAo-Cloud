package com.liao.datascope.output.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liao.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备详细
 * </p>
 *
 * @author LiAo
 * @since 2021-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DeviceDetail对象", description = "")
public class DeviceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备id")
    @TableId(value = "dd_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Excel(name = "设备序号")
    private Long ddId;

    @ApiModelProperty(value = "设备名称")
    @Excel(name = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备秘钥")
    @Excel(name = "设备秘钥")
    private String deviceSecret;

    @ApiModelProperty(value = "固件版本号")
    @Excel(name = "固件版本号")
    private String firmwareVersion;

    @ApiModelProperty(value = "设备的激活时间")
    @Excel(name = "设备的激活时间")
    private String gmtActive;

    @ApiModelProperty(value = "设备的创建时间")
    @Excel(name = "设备的创建时间")
    private String gmtCreate;

    @ApiModelProperty(value = "设备的唯一标识符")
    @TableField("IotId")
    @Excel(name = "设备的唯一标识符")
    private String IotId;

    @ApiModelProperty(value = "备注名称")
    @Excel(name = "备注名称")
    private String nickname;

    @ApiModelProperty(value = "0 设备  1 网关")
    @Excel(name = "0 设备  1 网关")
    private String nodeType;

    @ApiModelProperty(value = "产品key")
    @Excel(name = "产品key")
    private String productKey;

    @ApiModelProperty(value = "所属产品名称")
    @Excel(name = "所属产品名称")
    private String productName;

    @ApiModelProperty(value = "设备地域")
    @Excel(name = "设备地域")
    private String region;

    @ApiModelProperty(value = "状态： ONLINE：设备在线。 OFFLINE：设备离线。 UNACTIVE：设备未激活。 DISABLE：设备已禁用。")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "设备的激活时间")
    @Excel(name = "设备的激活时间")
    private String utcActive;

    @ApiModelProperty(value = "设备的创建时间")
    @Excel(name = "设备的创建时间")
    private String utcCreate;

    @ApiModelProperty(value = "乐观锁")
    @TableField(select = false)
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(select = false)
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @Excel(name = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
