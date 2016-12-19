package com.convenientbuy.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bonismo@hotmail.com
 * 下午9:43 on 16/12/19.
 */
@Controller
public class PageController {

    /**
     * 用户注册,跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    /**
     * 用户登录,并携带登陆后的跳转链接地址
     *
     * @param redirect 登录前的连接地址
     * @param model
     * @return
     */
    @RequestMapping("/page/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
