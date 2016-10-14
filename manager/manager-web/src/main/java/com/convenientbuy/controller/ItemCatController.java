package com.convenientbuy.controller;

import com.convenientbuy.common.pojo.EUTreeNode;
import com.convenientbuy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:16 on 16/10/14.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService service;

    /**
     * 获取商品列表
     * common.js
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    private List<EUTreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = service.getCatList(parentId);
        return list;
    }
}
