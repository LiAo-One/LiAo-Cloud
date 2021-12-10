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
 * 操作日志记录
 * </p>
 *
 * @author LiAo
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysOpenLog对象", description="操作日志记录")
public class SysOpenLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志id")
    @TableId(value = "oper_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long operId;

    @ApiModelProperty(value = "模块标题")
    private String operTitle;

    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer openBusinessType;

    @ApiModelProperty(value = "方法名称")
    private String openMethod;

    @ApiModelProperty(value = "请求方式")
    private String openRequestMethod;

    @ApiModelProperty(value = "主机地址IP")
    private String operIp;

    @ApiModelProperty(value = "请求url")
    private String openUrl;

    @ApiModelProperty(value = "地址")
    private String operLocation;

    @ApiModelProperty(value = "请求参数")
    private String operParam;

    @ApiModelProperty(value = "返回参数")
    private String openResult;

    @ApiModelProperty(value = "状态")
    private Integer openStatus;

    @ApiModelProperty(value = "错误消息")
    private String openErrorMsg;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
