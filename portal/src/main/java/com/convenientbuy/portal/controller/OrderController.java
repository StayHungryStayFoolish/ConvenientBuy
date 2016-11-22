package com.convenientbuy.portal.controller;

import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.portal.pojo.CartItem;
import com.convenientbuy.portal.pojo.Order;
import com.convenientbuy.portal.service.CartService;
import com.convenientbuy.portal.service.OrderService;
import org.joda.time.DateTime;
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

    /**
     * 创建订单
     *
     * @param order
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/create")
    public String createOrder(Order order, Model model, HttpServletRequest request) {
        // 需要获取订单用户的信息
        try {
            CbUser user = (CbUser) request.getAttribute("user");
            // 在order 中补全用户信息
            order.setUserId(user.getId());
            order.setBuyerNick(user.getUsername());
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "创建订单出错。请稍后重试！");
            return "error/exception";
        }
    }
}
