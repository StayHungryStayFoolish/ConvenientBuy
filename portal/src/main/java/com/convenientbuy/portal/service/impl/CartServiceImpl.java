package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.portal.pojo.CartItem;
import com.convenientbuy.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午9:31 on 16/11/14.
 */
@Service
public class CartServiceImpl implements CartService {

    // 服务层基础 URL
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    // 商品描述 URL
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    /**
     * 购物车添加商品
     * 1.先判断购物车是否有该商品,如果有,添加数量
     * 2.如果没有,需要获取商品基本信息,然后再添加到购物车
     *
     * @param itemId   商品 ID
     * @param num      数量
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        // 创建商品信息对象
        CartItem cartItem = null;
        // 获取购车列表信息
        List<CartItem> itemList = getCartItemList(request);
        // 解决需求 1
        for (CartItem cItem : itemList) {
            // 如果商品已存在,数量增加
            if (cartItem.getId() == itemId) {
                cItem.setNum(cartItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }
        // 解决需求 2
        if (cartItem == null) {
            cartItem = new CartItem();
            // 需要调用 Rest 层的 get 请求 , 获取 Json 数据
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            // 转换为对象
            Result result = Result.formatToPojo(json, CbItem.class);
            // 如果状态是200 ,获取数据
            if (result.getStatus() == 200) {
                CbItem item = (CbItem) result.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                // 如果图片数据 null,设置为空,不显示. 如果有,截取第一个图片展示
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
                cartItem.setNum(item.getNum());
                cartItem.setPrice(item.getPrice());
            }
            itemList.add(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
