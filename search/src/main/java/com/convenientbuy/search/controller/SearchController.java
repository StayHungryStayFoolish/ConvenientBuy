package com.convenientbuy.search.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.search.pojo.SearchResult;
import com.convenientbuy.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午11:47 on 16/12/10.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService service;

    /**
     * 根据查询条件/页码/显示数量返回数据
     *
     * @param queryString
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Result search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "60") Integer rows) {
        if (StringUtils.isBlank(queryString)) {
            return Result.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            searchResult = service.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(searchResult);
    }
}
