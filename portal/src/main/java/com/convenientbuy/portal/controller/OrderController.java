package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.service.CartService;
import com.convenientbuy.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by bonismo@hotmail.com
 * 下午10:55 on 16/11/22.
 * <p>
 * 订单操作
 */
@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


}
