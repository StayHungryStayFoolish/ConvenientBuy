package com.convenientbuy.controller;

import com.convenientbuy.common.pojo.EUDataGridResult;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午10:15 on 16/9/15.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据商品 ID 查询数据
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public CbItem getItemById(@PathVariable long itemId) {
        CbItem cbItem = itemService.getItemById(itemId);
        return cbItem;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * 添加商品基本信息/描述/规格参数
     *
     * @param item       商品
     * @param desc       商品描述
     * @param itemParams 规格参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    private Result createItem(CbItem item, String desc, String itemParams) throws Exception {
        Result result = itemService.createItem(item, desc, itemParams);
        return result;
    }
}
