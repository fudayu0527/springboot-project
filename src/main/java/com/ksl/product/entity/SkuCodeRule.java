package com.ksl.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * <p>
 * sku生成规则表 
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Data
@Accessors(chain = true)

@ApiModel("sku生成规则表 ")
public class SkuCodeRule implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    @ApiModelProperty("id")
    @TableField(value = "id")
    private Long id;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    @TableField(value = "rule_name")
    private String ruleName;

    /**
     * 适用品牌
     */
    @ApiModelProperty("适用品牌")
    @TableField(value = "brand")
    private String brand;

    /**
     * 规则内容
     */
    @ApiModelProperty("规则内容")
    @TableField(value = "content")
    private String content;

    /**
     * 编码范围
     */
    @ApiModelProperty("编码范围")
    @TableField(value = "code_range")
    private String codeRange;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField(value = "remark")
    private String remark;

    /**
     * 状态（0：启用，1：停用）
     */
    @ApiModelProperty("状态（0：启用，1：停用）")
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
