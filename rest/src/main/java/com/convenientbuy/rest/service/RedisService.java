package com.convenientbuy.rest.service;

import com.convenientbuy.common.pojo.Result;

/**
 * Created by bonismo@hotmail.com
 * 下午11:29 on 16/11/29.
 */
public interface RedisService {

    /**
     * 根据商品分类 ID 同步 Redis 缓存数据
     *
     * @param contentCid
     * @return
     */
    Result syncContent(long contentCid);

}
