package com.convenientbuy.portal.service;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午9:30 on 17/10/6.
 */
public interface CartService {

    /**
     * 购物车添加商品
     *
     * @param itemId   商品 ID
     * @param num      数量
     * @param request
     * @param response
     * @return
     */
    Result addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取购车列表
     *
     * @param request
     * @param response
     * @return
     */
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

    /**
     * 购物车根据商品 ID 进行删除
     *
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    Result deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

}
