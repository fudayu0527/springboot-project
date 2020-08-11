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
 * 外部sku 
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Data
@Accessors(chain = true)

@ApiModel("外部sku ")
public class ExternalSku implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    @ApiModelProperty("id")
    @TableField(value = "id")
    private Long id;

    /**
     * 外部sku
     */
    @ApiModelProperty("外部sku")
    @TableField(value = "external_sku_code")
    private String externalSkuCode;

    /**
     * 同款sku
     */
    @ApiModelProperty("同款sku")
    @TableField(value = "same_sku_code")
    private String sameSkuCode;

    /**
     * 库存sku
     */
    @ApiModelProperty("库存sku")
    @TableField(value = "sku_code")
    private String skuCode;

    /**
     * 对应账号
     */
    @ApiModelProperty("对应账号")
    @TableField(value = "account")
    private String account;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否已删除（0：未删除，1：已删除）
     */
    @ApiModelProperty("是否已删除（0：未删除，1：已删除）")
    @TableField(value = "deleted")
    private Integer deleted;

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
