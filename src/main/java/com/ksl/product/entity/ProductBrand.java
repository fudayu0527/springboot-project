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
 * 产品品牌表 
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Data
@Accessors(chain = true)

@ApiModel("产品品牌表 ")
public class ProductBrand implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    @ApiModelProperty("id")
    @TableField(value = "id")
    private Long id;

    /**
     * 品牌代码
     */
    @ApiModelProperty("品牌代码")
    @TableField(value = "brand_code")
    private String brandCode;

    /**
     * 品牌中文名称
     */
    @ApiModelProperty("品牌中文名称")
    @TableField(value = "brand_name_cn")
    private String brandNameCn;

    /**
     * 品牌英文名称
     */
    @ApiModelProperty("品牌英文名称")
    @TableField(value = "brand_name_en")
    private String brandNameEn;

    /**
     * 品牌logo URL
     */
    @ApiModelProperty("品牌logo URL")
    @TableField(value = "brand_logo")
    private String brandLogo;

    /**
     * 关联类目id
     */
    @ApiModelProperty("关联类目id")
    @TableField(value = "untitled")
    private String untitled;

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
