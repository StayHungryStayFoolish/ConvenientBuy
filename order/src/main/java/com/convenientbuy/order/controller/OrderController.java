package com.convenientbuy.order.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.ExceptionUtil;
import com.convenientbuy.order.pojo.Order;
import com.convenientbuy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午11:03 on 16/12/26.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService service;

    /**
     * 创建订单
     *
     * @param order
     * @return
     * @RequestBody 用来接收 json 对象字符串,不是 json 对象本身
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result createOrder(@RequestBody Order order) {
        try {
            Result result = service.createOrder(order, order.getOrderItems(), order.getOrderShipping());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
