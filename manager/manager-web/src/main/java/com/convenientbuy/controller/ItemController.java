package com.convenientbuy.controller;

import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午10:15 on 16/9/15.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public CbItem getItemById(@PathVariable long itemId) {
        CbItem cbItem = itemService.getItemById(itemId);
        return cbItem;
    }
}
