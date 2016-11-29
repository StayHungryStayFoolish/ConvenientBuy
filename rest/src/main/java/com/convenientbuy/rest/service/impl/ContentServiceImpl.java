package com.convenientbuy.rest.service.impl;

import com.convenientbuy.mapper.CbContentMapper;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.rest.dao.JedisClient;
import com.convenientbuy.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午7:54 on 16/11/8.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CbContentMapper mapper;
    @Autowired
    private JedisClient jedisClient;

    // Redis 商品内容 KRY
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<CbContent> getContentList(long contentId) {
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
