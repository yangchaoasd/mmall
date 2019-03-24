package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVO;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-29 20:45
 **/
public interface CartService {

    ServerResponse add(int userId, int count, int productId);

    ServerResponse<CartVO> update(int userId, int count, int productId);

    ServerResponse<CartVO> deleteProduct(int userId, String productIds);

    ServerResponse<CartVO> list(int userId);

    ServerResponse<CartVO> selectOrUnSelect(int userId, Integer productId, int checked);

    ServerResponse<Integer> getCartProductCount(int userId);
}
