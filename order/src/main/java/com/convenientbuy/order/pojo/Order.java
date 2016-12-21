package com.convenientbuy.order.pojo;

import com.convenientbuy.pojo.CbOrder;
import com.convenientbuy.pojo.CbOrderItem;
import com.convenientbuy.pojo.CbOrderShipping;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午8:17 on 16/12/21.
 */
public class Order extends CbOrder {

    // 订单
    private List<CbOrderItem> orderItems;

    // 物流
    private CbOrderShipping orderShipping;

    public List<CbOrderItem> getOrderItems() {

        return orderItems;
    }

    public void setOrderItems(List<CbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public CbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(CbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
