package com.convenientbuy.rest.controller;

import com.convenientbuy.rest.pojo.CatResult;
import com.convenientbuy.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午9:55 on 16/12/7.
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService service;

    /**
     * jsonp 跨域请求获取商品分类列表
     * item.jsp , 通过 lib-v1.js 调用
     *
     * @param callback
     * @return
     */
    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = service.getItemCatList();
        // Spring 支持 jsonp 跨域请求
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
