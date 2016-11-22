package com.convenientbuy.portal.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.portal.pojo.CartItem;
import com.convenientbuy.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:10 on 16/11/22.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    /**
     * 添加商品到购物车
     *
     * @param itemId   商品 ID
     * @param num      数量
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue = "1") Integer num,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        Result result = service.addCartItem(itemId, num, request, response);
        return "redirect:/cart/success.html";
    }

    /**
     * 返回商品添加成功页面
     *
     * @return
     */
    @RequestMapping("/success")
    public String showSuccess() {
        return "cartSuccess";
    }

    /**
     * 获取购物车列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = service.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "cart";
    }

    /**
     * 购物车根据商品 ID删除
     *
     * @param itemId   商品 ID
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        service.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }
}
