package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVO;

import java.util.Map;

public interface OrderService {

    ServerResponse<OrderVO> createOrder(int userId, int shippingId);

    ServerResponse<String> cancel(int userId, Long orderNo);

    ServerResponse getOrderCartProduct(int userId);

    ServerResponse<OrderVO> getOrderDetail(int userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(int userId, int pageNum, int pageSize);


    ServerResponse pay(Long orderNo, int userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse<Boolean> queryOrderPayStatus(int userId, Long orderNo);


    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVO> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Long orderNo);

    // hour个小时未付款的订单，进行关闭
    void closeOrder(int hour);
}
