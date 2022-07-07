package com.zgz.serviceorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgz.commonutils.Result;
import com.zgz.commonutils.utils.JwtUtils;
import com.zgz.serviceorder.pojo.TOrder;
import com.zgz.serviceorder.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-12-06
 */
@Api(description="前台课程展示")
@RestController
@RequestMapping("/serviceorder/t-order")
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    @ApiOperation(value = "根据课程id、用户id创建订单")
    @PostMapping("/createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId,memberId);
        return Result.succ().data("orderNo",orderNo);
    }

    @ApiOperation(value = "根据订单编号查询订单信息")
    @GetMapping("/getOrderInfo/{orderNo}")
    public Result getOrderInfo(@PathVariable String orderNo){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder tOrder = orderService.getOne(queryWrapper);
        return Result.succ().data("tOrder",tOrder);
    }

}

