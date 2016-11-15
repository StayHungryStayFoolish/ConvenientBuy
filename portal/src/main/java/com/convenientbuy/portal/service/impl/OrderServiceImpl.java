package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.portal.pojo.Order;
import com.convenientbuy.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by bonismo@hotmail.com
 * 下午10:34 on 16/11/15.
 */
public class OrderServiceImpl implements OrderService {

    // Order 系统基础 URL
    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    // Order 系统创建订单 URL
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    /**
     * 创建订单,获取订单 ID
     *
     * @param order
     * @return
     */
    @Override
    public String createOrder(Order order) {
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJSON(order));
        Result result = Result.format(json);
        if (result.getStatus() == 200) {
            Object orderId = result.getData();
            return orderId.toString();
        }
        return "";
    }
}
