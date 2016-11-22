package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.pojo.CartItem;
import com.convenientbuy.portal.service.CartService;
import com.convenientbuy.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 展示购物车商品列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 查询购物车商品列表
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "order-cart";
    }


}
