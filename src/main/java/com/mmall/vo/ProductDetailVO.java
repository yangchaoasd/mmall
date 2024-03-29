package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 20:41
 **/
@Data
public class ProductDetailVO {

    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private String subImage;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private String createTime;
    private String updateTime;
    private String imageHost;
    private Integer parentCategoryId;
}
