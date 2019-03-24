package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 20:14
 **/
@Data
public class OrderProductVO {

    private List<OrderItemVO> orderItemVoList;
    private BigDecimal productTotalPrice;
    private String imageHost;
}
