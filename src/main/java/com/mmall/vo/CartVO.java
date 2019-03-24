package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 20:14
 **/
@Data
public class CartVO {

    private List<CartProductVO> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选
    private String imageHost;
}
