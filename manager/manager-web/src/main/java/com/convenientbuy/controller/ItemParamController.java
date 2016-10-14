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

    /**
     * 根据商品 ID 查询规格
     * item-param-add.jsp
     *
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public Result getItemParamByCid(@PathVariable Long itemCatId) {
        Result result = service.getItemParamByCid(itemCatId);
        return result;
    }

    /**
     * 添加商品规格
     * item-param-add.jsp
     *
     * @param cid
     * @param paramData
     * @return
     */
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
