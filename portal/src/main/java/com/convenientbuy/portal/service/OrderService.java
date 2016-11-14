package com.convenientbuy.portal.service;

import com.convenientbuy.portal.pojo.Order;

/**
 * Created by bonismo@hotmail.com
 * 下午9:49 on 16/11/14.
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    String createOrder(Order order);
}
