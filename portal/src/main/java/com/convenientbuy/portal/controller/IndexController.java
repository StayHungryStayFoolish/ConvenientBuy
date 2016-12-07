package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午10:20 on 16/11/22.
 * <p>
 * Index 页面
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService service;

    /**
     * 访问首页,展示轮播图内容
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = service.getContenList();
        model.addAttribute("ad1", adJson);
        return "index";
    }

    /**
     * POST 请求,使用 method 声明, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8" 处理 Post 请求乱码问题
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/httpclient/post", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String testPost(String username, String password) {
        String string = "username:" + username + "\tpassword:" + password;
        System.out.println(string);
        return "username:" + username + ",password:" + password;
    }
}
