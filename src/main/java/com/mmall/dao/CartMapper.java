package com.mmall.dao;

import com.mmall.model.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") int userId, @Param("productId") int productId);

    List<Cart> selectCartByUserId(int userId);

    int selectCartProductCheckedStatusByUserId(int userId);

    int deleteByUserIdProductIds(@Param("userId") int userId, @Param("productIdList") List<String> productIdList);

    int checkedOrUncheckedProduct(@Param("userId") int userId, @Param("productId") Integer productId, @Param("checked") int checked);

    int selectCartProductCount(int userId);

    List<Cart> selectCheckedCartByUesrId(int userId);
}