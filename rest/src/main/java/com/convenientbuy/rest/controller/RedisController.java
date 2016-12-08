package com.convenientbuy.rest.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午10:35 on 16/12/8.
 */
public class RedisController {

    @Autowired
    private RedisService service;

    /**
     * 根据内容 ID 同步 Redis 缓存
     *
     * @param contentCid
     * @return
     */
    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public Result contentCacheSync(@PathVariable Long contentCid) {
        Result result = service.syncContent(contentCid);
        return result;
    }
}
