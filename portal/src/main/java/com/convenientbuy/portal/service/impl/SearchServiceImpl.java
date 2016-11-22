package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.portal.pojo.SearchResult;
import com.convenientbuy.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午11:03 on 16/11/22.
 */
@Service
public class SearchServiceImpl implements SearchService {

    // Solr 搜索服务基础 URL
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    /**
     * 搜索服务
     *
     * @param queryString
     * @param page
     * @return
     */
    @Override
    public SearchResult search(String queryString, int page) {
        // 防止查询参数
        Map<String, String> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page", String.valueOf(page));
        try {
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            Result result = Result.formatToPojo(json, SearchResult.class);
            if (result.getStatus() == 200) {
                SearchResult searchResult = (SearchResult) result.getData();
                return searchResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
