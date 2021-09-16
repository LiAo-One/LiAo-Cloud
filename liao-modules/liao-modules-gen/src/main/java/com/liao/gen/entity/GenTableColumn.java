package com.liao.gen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liao.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 代码生成业务表字段
 * </p>
 *
 * @author LiAo
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GenTableColumn对象", description = "代码生成业务表字段")
public class GenTableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "column_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long columnId;

    @ApiModelProperty(value = "归属表编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tableId;

    @ApiModelProperty(value = "列名称")
    private String columnName;

    @ApiModelProperty(value = "列描述")
    private String columnComment;

    @ApiModelProperty(value = "列类型")
    private String columnType;

    @ApiModelProperty(value = "JAVA类型")
    private String javaType;

    @ApiModelProperty(value = "JAVA字段名")
    private String javaField;

    @ApiModelProperty(value = "是否主键（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isPk;

    @ApiModelProperty(value = "是否自增（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isIncrement;

    @ApiModelProperty(value = "是否必填（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isRequired;

    @ApiModelProperty(value = "是否为插入字段（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isInsert;

    @ApiModelProperty(value = "是否编辑字段（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isEdit;

    @ApiModelProperty(value = "是否列表字段（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isList;

    @ApiModelProperty(value = "是否查询字段（1是）")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String isQuery;

    @ApiModelProperty(value = "查询方式（等于、不等于、大于、小于、范围）")
    private String queryType;

    @ApiModelProperty(value = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    private String htmlType;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    /**
     * 判断是否为主键
     * @return 结果
     */
    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    /**
     * 判断是否为时间参数
     * @return 结果
     */
    public boolean isSuperColumn()
    {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField)
    {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime", "remark",
                // TreeEntity
                "parentName", "parentId", "orderNum", "ancestors");
    }

}
