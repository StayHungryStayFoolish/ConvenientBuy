package com.convenientbuy.portal.pojo;

import com.convenientbuy.pojo.CbOrder;
import com.convenientbuy.pojo.CbOrderItem;
import com.convenientbuy.pojo.CbOrderShipping;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午9:27 on 17/10/6.
 * <p>
 * 补全订单信息使用
 */
public class Order extends CbOrder {

    private List<CbOrderItem> orderItems;

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
