package com.convenientbuy.controller;

import com.convenientbuy.common.pojo.EUTreeNode;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:22 on 16/10/14.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService service;

    /**
     * 获取商品分类列表,默认0,打开父类
     * content.jsp
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = service.getCategoryList(parentId);
        return list;
    }

    /**
     * 商品TREE,创建节点
     * content.jsp
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Result createContentCategory(Long parentId, String name) {
        Result result = service.insertContentCategory(parentId, name);
        return result;
    }
}
