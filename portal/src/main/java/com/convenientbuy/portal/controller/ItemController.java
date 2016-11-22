package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.pojo.ItemInfo;
import com.convenientbuy.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午10:29 on 16/11/22.
 * <p>
 * 商品信息展示
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService service;

    /**
     * 根据商品 ID 获取基本信息
     *
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        ItemInfo item = service.getItemById(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * 根据商品 ID 获取描述信息
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId) {
        String string = service.getItemDescById(itemId);
        return string;
    }

    /**
     * 根据商品 ID 获取规格参数
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId) {
        String string = service.getItemParam(itemId);
        return string;
    }
}
