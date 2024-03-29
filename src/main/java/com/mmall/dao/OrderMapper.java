package com.mmall.dao;

import com.mmall.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userId") int userId, @Param("orderNo") Long orderNo);

    Order selectByOrderNo(Long orderNo);

    List<Order> selectByUserId(int userId);

    List<Order> selectAllOrder();

    List<Order> selectOrderStatusByCreateTime(@Param("status") Integer status, @Param("date") String date);

    int closeOrderByOrderId(Integer id);
}