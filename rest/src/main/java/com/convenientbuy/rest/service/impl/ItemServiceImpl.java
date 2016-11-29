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
     * 1.从缓存获取 2.缓存没有,从数据库获取 3.添加查询到的数据到缓存中
     *
     * @param itemId
     * @return
     */
    @Override
    public Result getItemBaseInfo(long itemId) {
        try {
            // 1.缓存获取
            // 根据商品 ID 从缓存中获取商品信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if (!StringUtils.isBlank(json)) {
                CbItem item = JsonUtils.jsonToPOJO(json, CbItem.class);
                return Result.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.从数据库查询
        CbItem item = itemMapper.selectByPrimaryKey(itemId);
        // 3.从新添加至缓存
        try {
            // 需要将 item 转换为 json 数据
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJSON(item));
            // 从新设置缓存失效时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(item);
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
