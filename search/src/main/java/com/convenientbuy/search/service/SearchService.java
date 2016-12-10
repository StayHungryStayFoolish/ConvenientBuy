package com.convenientbuy.search.service;

import com.convenientbuy.search.pojo.SearchResult;

/**
 * Created by bonismo@hotmail.com
 * 下午10:41 on 16/12/10.
 */
public interface SearchService {

    /**
     * 根据条件,页码,数量进行搜索
     *
     * @param queryString
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    SearchResult search(String queryString, int page, int rows) throws Exception;

}
