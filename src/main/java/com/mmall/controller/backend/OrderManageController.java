package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.OrderService;
import com.mmall.service.UserService;
import com.mmall.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/manage/order", description = "后台管理订单接口")
@RestController
@RequestMapping(value = "/manage/order", method = {RequestMethod.GET, RequestMethod.POST})
public class OrderManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int")
    })
    @RequestMapping("/list")
    public ServerResponse<PageInfo> orderList(HttpServletRequest request,
                                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return orderService.manageList(pageNum, pageSize);
    }

    @ApiOperation(value = "订单详情")
    @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单id", required = true, dataType = "long")
    @RequestMapping("/detail")
    public ServerResponse<OrderVO> orderDetail(HttpServletRequest request, @RequestParam Long orderNo) {

        return orderService.manageDetail(orderNo);
    }


    @ApiOperation(value = "根据订单id搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int")
    })
    @RequestMapping("/search")
    public ServerResponse<PageInfo> orderSearch(HttpServletRequest request,
                                                @RequestParam Long orderNo,
                                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return orderService.manageSearch(orderNo, pageNum, pageSize);
    }


    @ApiOperation(value = "发货")
    @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单id", required = true, dataType = "long")
    @RequestMapping("/send_goods")
    public ServerResponse<String> orderSendGoods(HttpServletRequest request, @RequestParam Long orderNo) {

        return orderService.manageSendGoods(orderNo);
    }
}
