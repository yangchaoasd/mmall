package com.mmall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "Shipping", description = "地址")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    @ApiModelProperty(value = "地址id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "收货姓名")
    private String receiverName;

    @ApiModelProperty(value = "收货手机号")
    private String receiverPhone;

    @ApiModelProperty(value = "电话")
    private String receiverMobile;

    @ApiModelProperty(value = "省份")
    private String receiverProvince;

    @ApiModelProperty(value = "城市")
    private String receiverCity;

    @ApiModelProperty(value = "区域")
    private String receiverDistrict;

    @ApiModelProperty(value = "地址")
    private String receiverAddress;

    @ApiModelProperty(value = "邮编")
    private String receiverZip;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后一次更新时间")
    private Date updateTime;
}