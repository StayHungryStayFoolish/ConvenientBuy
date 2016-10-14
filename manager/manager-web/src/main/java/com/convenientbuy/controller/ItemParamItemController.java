package com.convenientbuy.controller;

import com.convenientbuy.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bonismo@hotmail.com
 * 下午11:26 on 16/10/14.
 */
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService service;

    /**
     * 根据商品 ID 显示规格
     *
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/showitem/{itemId}")
    public String showItemParam(@PathVariable Long itemId, Model model) {
        String string = service.getItemParamByItemId(itemId);
        model.addAttribute("itemParam", string);
        return "item";
    }
}
