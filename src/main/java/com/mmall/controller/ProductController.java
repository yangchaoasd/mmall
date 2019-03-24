package com.mmall.controller;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.ProductService;
import com.mmall.vo.ProductDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-29 19:33
 **/
@Api(value = "/product", description = "产品接口")
@RestController
@RequestMapping(value = "/product", method = {RequestMethod.GET, RequestMethod.POST})
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "查询产品详情")
    @ApiImplicitParam(paramType = "query", name = "productId", value = "产品id", required = true, dataType = "int")
    @RequestMapping("/{productId}")
    public ServerResponse<ProductDetailVO> detail(@PathVariable int productId) {

        return productService.getProductDetailVo(productId);
    }

    @ApiOperation(value = "查看产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "关键字", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "orderBy", value = "排序", defaultValue = "", dataType = "String")
    })
    @RequestMapping("/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse<PageInfo> list1(@PathVariable String keyword,
                                          @PathVariable Integer pageNum,
                                          @PathVariable Integer pageSize,
                                          @PathVariable String orderBy) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }

        return productService.getProductByKeywordCategory(keyword, null, pageNum, pageSize, orderBy);
    }

    @ApiOperation(value = "查看产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "分类id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "orderBy", value = "排序", defaultValue = "", dataType = "String")
    })
    @RequestMapping("/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse<PageInfo> list2(@PathVariable Integer categoryId,
                                          @PathVariable Integer pageNum,
                                          @PathVariable Integer pageSize,
                                          @PathVariable String orderBy) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }

        return productService.getProductByKeywordCategory(null, categoryId, pageNum, pageSize, orderBy);
    }
}

