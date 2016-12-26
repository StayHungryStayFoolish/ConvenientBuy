package com.convenientbuy.order.service;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbOrder;
import com.convenientbuy.pojo.CbOrderItem;
import com.convenientbuy.pojo.CbOrderShipping;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:44 on 16/12/26.
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     * @param itemList
     * @param orderShipping
     * @return
     */
    Result createOrder(CbOrder order, List<CbOrderItem> itemList, CbOrderShipping orderShipping);

}
