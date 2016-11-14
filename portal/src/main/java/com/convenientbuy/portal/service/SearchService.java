package com.convenientbuy.portal.service;

import com.convenientbuy.portal.pojo.SearchResult;

/**
 * Created by bonismo@hotmail.com
 * 下午9:53 on 16/11/14.
 */
public interface SearchService {

    /**
     * 根据条件查询订单
     *
     * @param queryString
     * @param page
     * @return
     */
    SearchResult search(String queryString, int page);
}
