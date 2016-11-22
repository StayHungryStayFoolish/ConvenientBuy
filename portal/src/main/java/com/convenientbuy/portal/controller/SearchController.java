package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.pojo.SearchResult;
import com.convenientbuy.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * Created by bonismo@hotmail.com
 * 下午11:22 on 16/11/22.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService service;

    /**
     * 根据条件查询商品
     *
     * @param queryString
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) {
        if (null != queryString) {
            try {
                // 浏览器西欧编码 ISO8859-1
                queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult searchResult = service.search(queryString, page);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }
}
