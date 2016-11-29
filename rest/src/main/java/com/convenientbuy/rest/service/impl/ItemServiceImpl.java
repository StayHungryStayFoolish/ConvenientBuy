package com.convenientbuy.rest.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.mapper.CbItemDescMapper;
import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.mapper.CbItemParamItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.rest.dao.JedisClient;
import com.convenientbuy.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * Created by bonismo@hotmail.com
 * 下午11:45 on 16/11/29.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CbItemMapper itemMapper;

    @Autowired
    private CbItemDescMapper itemDescMapper;

    @Autowired
    private CbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    // Redis 商品KEY
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    // Redis KEY 有效期,单位秒
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    /**
     * 根据商品 ID 获取基本信息
     *
     * @param itemId
     * @return
     */
    @Override
    public Result getItemBaseInfo(long itemId) {
        try {
            // 根据商品 ID 从缓存中获取商品信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if (!StringUtils.isBlank(json)) {
                CbItem item = JsonUtils.jsonToPOJO(json, CbItem.class);
                return Result.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result getItemDesc(long itemId) {
        return null;
    }

    @Override
    public Result getItemParam(long itemId) {
        return null;
    }
}
