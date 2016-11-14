package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.portal.service.ContentService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午10:25 on 16/11/14.
 */
@Service
public class ContentServiceImpl implements ContentService {

    // Rset 层基础 URL
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    // 首页轮播图 URL
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContenList() {
        // 调用 Rest 层 get 请求服务
        String indexResult = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        try {
            // 使用 Result 来接受 请求到的数据
            Result result = Result.formatToList(indexResult, CbContent.class);
            // 获取轮播图内容类别
            List<CbContent> list = (List<CbContent>) result.getData();
            List<Map> resultList = new ArrayList<>();
            for (CbContent cbContent : list) {

            }
        }
        return null;
    }
}
