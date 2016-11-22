package com.convenientbuy.portal.controller;

import com.convenientbuy.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by bonismo@hotmail.com
 * 下午11:22 on 16/11/22.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService service;
}
