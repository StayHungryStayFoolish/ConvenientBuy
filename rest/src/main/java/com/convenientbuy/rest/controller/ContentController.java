package com.convenientbuy.rest.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.ExceptionUtil;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午9:27 on 16/12/7.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService service;

    /**
     * 调用轮播图 url,展示页面
     *
     * @param contentCategoryId
     * @return
     */
    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public Result getContentList(@PathVariable Long contentCategoryId) {
        try {
            List<CbContent> list = service.getContentList(contentCategoryId);
            return Result.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
