package com.convenientbuy.search.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午11:38 on 16/12/10.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService service;

    /**
     * 导入数据到 Solr 索引库
     *
     * @return
     */
    @RequestMapping("importAll")
    @ResponseBody
    public Result importAllItems() {
        Result result = service.importAllItems();
        return result;
    }
}
