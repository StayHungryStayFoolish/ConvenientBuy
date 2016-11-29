package com.convenientbuy.rest.service.impl;

import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.mapper.CbContentMapper;
import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.pojo.CbContentExample;
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
     * 1.需要查询缓存中是否有,2.如果没有从数据库获取,3.然后再让入 Redis 缓存中
     *
     * @param contentId
     * @return
     */
    @Override
    public List<CbContent> getContentList(long contentId) {
        // 1.缓存获取
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
        // 2.缓存中没有,需要从数据库查询
        CbContentExample example = new CbContentExample();
        CbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentId);
        // 根据条件查询
        List<CbContent> list = mapper.selectByExample(example);

        // 3.查询后,需要将结构转换为 Json 数据,存入到 Redis 缓存中
        try {
            String cacheString = JsonUtils.objectToJSON(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId), cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
