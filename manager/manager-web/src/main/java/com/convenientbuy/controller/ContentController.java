package com.convenientbuy.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午11:01 on 16/10/14.
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService service;

    /**
     * 添加内容
     * content-add.jsp
     *
     * @param content
     * @return
     */
    @RequestMapping("/content/save")
    @ResponseBody
    public Result insertContent(CbContent content) {
        Result result = service.insertContent(content);
        return result;
    }
}
