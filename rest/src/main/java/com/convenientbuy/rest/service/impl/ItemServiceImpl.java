package com.convenientbuy.rest.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.mapper.CbItemDescMapper;
import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.mapper.CbItemParamItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.pojo.CbItemDesc;
import com.convenientbuy.pojo.CbItemParamItem;
import com.convenientbuy.pojo.CbItemParamItemExample;
import com.convenientbuy.rest.dao.JedisClient;
import com.convenientbuy.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 1.从缓存获取 2.缓存没有,从数据库获取 3.添加查询到的数据到缓存中,并设置失效时间
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

    /**
     * 根据商品 ID 获取描述信息
     * 1.从缓存获取 2.缓存没有,从数据库获取 3.添加查询到的数据到缓存中,并设置失效时间
     *
     * @param itemId
     * @return
     */
    @Override
    public Result getItemDesc(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isBlank(json)) {
                CbItemDesc itemDesc = JsonUtils.jsonToPOJO(json, CbItemDesc.class);
                return Result.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJSON(itemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(itemDesc);
    }

    /**
     * 根据商品 ID 获取规格参数
     * 1.从缓存获取 2.缓存没有,从数据库获取 3.添加查询到的数据到缓存中,并设置失效时间
     *
     * @param itemId
     * @return
     */
    @Override
    public Result getItemParam(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(json)) {
                CbItemParamItem paramItem = JsonUtils.jsonToPOJO(json, CbItemParamItem.class);
                return Result.ok(paramItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建查询条件
        CbItemParamItemExample example = new CbItemParamItemExample();
        CbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        // 查询商品规格参数
        List<CbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        // 当不为 null, 且 size 大于 0 时
        if (null != list && list.size() > 0) {
            // 获取第一个
            CbItemParamItem paramItem = list.get(0);
            try {
                // 写入缓存
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJSON(paramItem));
                // 设置有效期
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.ok(paramItem);
        }
        return Result.build(400, "无此商品规格");
    }
}
