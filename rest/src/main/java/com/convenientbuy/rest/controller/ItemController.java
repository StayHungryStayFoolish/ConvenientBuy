package com.convenientbuy.rest.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午9:48 on 16/12/7.
 * <p>
 * 商品信息操作
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    /**
     * 根据商品 ID 获取基本信息
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public Result getItemBaseInfo(@PathVariable Long itemId) {
        Result result = service.getItemBaseInfo(itemId);
        return result;
    }

    /**
     * 根据商品 ID 获取描述信息
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public Result getItemDesc(@PathVariable Long itemId) {
        Result result = service.getItemDesc(itemId);
        return result;
    }

    /**
     * 根据商品 ID 获取规格参数
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public Result getItemParam(@PathVariable Long itemId) {
        Result result = service.getItemParam(itemId);
        return result;
    }
}
