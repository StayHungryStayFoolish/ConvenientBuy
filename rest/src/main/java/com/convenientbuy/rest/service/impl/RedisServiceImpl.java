package com.convenientbuy.rest.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.ExceptionUtil;
import com.convenientbuy.rest.dao.JedisClient;
import com.convenientbuy.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 上午12:11 on 16/11/30.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    /**
     * 同步首页轮播图缓存数据
     *
     * @param contentCid
     * @return
     */
    @Override
    public Result syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCid));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(400, ExceptionUtil.getStackTrace(e));
        }
        return Result.ok();
    }
}
