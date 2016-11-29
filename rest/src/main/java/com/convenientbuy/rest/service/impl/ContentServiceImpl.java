package com.convenientbuy.rest.service.impl;

import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.mapper.CbContentMapper;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.rest.dao.JedisClient;
import com.convenientbuy.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 需要查询缓存中是否有,如果没有从数据库获取
     * @param contentId
     * @return
     */
    @Override
    public List<CbContent> getContentList(long contentId) {
        try {
            // 根据 ID 通过 JedisClient 从 Redis 换从中获取
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId));
            if (!StringUtils.isBlank(result)) {
                List<CbContent> contentList = JsonUtils.jsonToList(result, CbContent.class);
                return contentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
