package com.convenientbuy.sso.controller;

import com.convenientbuy.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午9:47 on 16/12/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData() {
        return null;
    }
}
