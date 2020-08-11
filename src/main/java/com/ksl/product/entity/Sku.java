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
 * sku信息表
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Data
@Accessors(chain = true)

@ApiModel("sku信息表")
public class Sku implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 库存sku_code
     */
    @TableId(value = "sku_code")
    @ApiModelProperty("库存sku_code")
    @TableField(value = "sku_code")
    private String skuCode;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * sku列表预览图url
     */
    @ApiModelProperty("sku列表预览图url")
    @TableField(value = "sku_cover")
    private String skuCover;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    @TableField(value = "brand")
    private String brand;

    /**
     * 产品类别
     */
    @ApiModelProperty("产品类别")
    @TableField(value = "category")
    private String category;

    /**
     * 主体材质
     */
    @ApiModelProperty("主体材质")
    @TableField(value = "main_material")
    private String mainMaterial;

    /**
     * 供应商
     */
    @ApiModelProperty("供应商")
    @TableField(value = "supplier")
    private String supplier;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    @TableField(value = "principal")
    private String principal;

    /**
     * 工艺种类
     */
    @ApiModelProperty("工艺种类")
    @TableField(value = "process_type")
    private String processType;

    /**
     * 售卖方式
     */
    @ApiModelProperty("售卖方式")
    @TableField(value = "selling_method")
    private String sellingMethod;

    /**
     * 可售卖平台
     */
    @ApiModelProperty("可售卖平台")
    @TableField(value = "sales_platform")
    private String salesPlatform;

    /**
     * 主题元素
     */
    @ApiModelProperty("主题元素")
    @TableField(value = "theme_elements")
    private String themeElements;

    /**
     * 细分元素
     */
    @ApiModelProperty("细分元素")
    @TableField(value = "subdivided_elements")
    private String subdividedElements;

    /**
     * 场景
     */
    @ApiModelProperty("场景")
    @TableField(value = "scenes")
    private String scenes;

    /**
     * 性别(0、男，1、女，2、通用，)
     */
    @ApiModelProperty("性别(0、男，1、女，2、通用，)")
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 产品详情
     */
    @ApiModelProperty("产品详情")
    @TableField(value = "product_desc")
    private String productDesc;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField(value = "remark")
    private String remark;

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
