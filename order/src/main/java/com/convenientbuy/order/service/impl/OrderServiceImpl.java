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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    // 点单号生成的 KEY
    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    // 初始化的订单编号
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    // 订单明细 KEY
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    /**
     * 创建订单
     * 1.需要补全订单,特别是订单 ID 需从 Redis 的获取
     * 2.需要补全订单明细信息
     * 3.需要补全物流信息
     *
     * @param order
     * @param itemList
     * @param orderShipping
     * @return
     */
    @Override
    public Result createOrder(CbOrder order, List<CbOrderItem> itemList, CbOrderShipping orderShipping) {
        // 从 Redis 中获取订单号
        String string = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)) {
            // 如果没有,设置值
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        // 1- 补全订单信息
        order.setOrderId(String.valueOf(orderId));
        // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        // 0:未评价 1:已评价
        order.setBuyerRate(0);
        // 向订单表插入数据
        orderMapper.insert(order);

// 2- 补全订单明细信息
        for (CbOrderItem cbOrderItem : itemList) {
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            cbOrderItem.setId(String.valueOf(orderDetailId));
            cbOrderItem.setOrderId(String.valueOf(orderId));
            // 添加到订单名字表中
            orderItemMapper.insert(cbOrderItem);
        }

        // 3- 补全物流信息
        orderShipping.setOrderId(String.valueOf(orderId));
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        // 添加
        shippingMapper.insert(orderShipping);

        // 携带订单 ID 返回
        return Result.ok(orderId);
    }
}
