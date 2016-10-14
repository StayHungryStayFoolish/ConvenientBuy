package com.convenientbuy.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbItemParam;
import com.convenientbuy.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午11:21 on 16/10/14.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService service;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public Result getItemParamByCid(@PathVariable Long itemCatId) {
        Result result = service.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public Result insertItemParam(@PathVariable Long cid, String paramData) {
        //创建pojo对象
        CbItemParam itemParam = new CbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        Result result = service.insertItemParam(itemParam);
        return result;
    }
}
