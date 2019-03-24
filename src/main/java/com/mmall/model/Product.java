package com.mmall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "User", description = "用户")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @ApiModelProperty(value = "产品id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "副标题")
    private String subtitle;

    @ApiModelProperty(value = "主图")
    private String mainImage;

    @ApiModelProperty(value = "子图")
    private String subImages;

    @ApiModelProperty(value = "详细")
    private String detail;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销售状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后一次更新时间")
    private Date updateTime;
}