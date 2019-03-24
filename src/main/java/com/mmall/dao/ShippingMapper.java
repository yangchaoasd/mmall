package com.mmall.dao;

import com.mmall.model.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByShippingIdAndUserId(@Param("userId") int userId, @Param("shippingId") int shippingId);

    int updateByShipping(Shipping record);

    Shipping selectByShippingAndUserId(@Param("userId") int userId, @Param("shippingId") int shippingId);

    List<Shipping> selectByUserId(@Param("userId") int userId);
}