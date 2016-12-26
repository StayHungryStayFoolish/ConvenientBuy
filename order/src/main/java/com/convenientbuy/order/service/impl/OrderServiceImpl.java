package com.convenientbuy.order.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbOrderItemMapper;
import com.convenientbuy.mapper.CbOrderMapper;
import com.convenientbuy.mapper.CbOrderShippingMapper;
import com.convenientbuy.order.dao.JedisClient;
import com.convenientbuy.order.service.OrderService;
import com.convenientbuy.pojo.CbOrder;
import com.convenientbuy.pojo.CbOrderItem;
import com.convenientbuy.pojo.CbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:46 on 16/12/26.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CbOrderMapper orderMapper;

    @Autowired
    private CbOrderItemMapper orderItemMapper;

    @Autowired
    private CbOrderShippingMapper shippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Result createOrder(CbOrder order, List<CbOrderItem> itemList, CbOrderShipping orderShipping) {
        return null;
    }
}
