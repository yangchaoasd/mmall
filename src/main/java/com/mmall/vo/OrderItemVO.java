package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 20:14
 **/
@Data
public class OrderItemVO {

    private Long orderNo;
    private Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String createTime;
}
