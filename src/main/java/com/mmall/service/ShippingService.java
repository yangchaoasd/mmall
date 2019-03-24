package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.model.Shipping;

public interface ShippingService {

    ServerResponse add(int userId, Shipping shipping);

    ServerResponse<String> delete(int userId, int shippingId);

    ServerResponse<String> update(int userId, Shipping shipping);

    ServerResponse<Shipping> select(int userId, int shippingId);

    ServerResponse<PageInfo> select(int userId, int pageNum, int pageSize);
}
