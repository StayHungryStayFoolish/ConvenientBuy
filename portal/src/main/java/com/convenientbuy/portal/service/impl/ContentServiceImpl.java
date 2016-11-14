package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 获取首页轮播图内容
     *
     * @return
     */
    @Override
    public String getContenList() {
        // 调用 Rest 层 get 请求服务
        String indexResult = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        try {
            // 使用 Result 来接受 请求到的数据
            Result result = Result.formatToList(indexResult, CbContent.class);
            // 获取轮播图内容列表
            List<CbContent> list = (List<CbContent>) result.getData();
            // 建立 Map, 用来存储轮播图信息
            List<Map> resultList = new ArrayList<>();
            // 遍历 轮播图列表,不全轮播图的内容
            for (CbContent cbContent : list) {
                Map map = new HashMap();
                map.put("src", cbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", cbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", cbContent.getUrl());
                map.put("alt", cbContent.getSubTitle());
                resultList.add(map);
            }
            // 返回页面需要的数据
            return JsonUtils.objectToJSON(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
