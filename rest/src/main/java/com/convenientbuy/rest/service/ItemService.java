package com.convenientbuy.rest.service;

import com.convenientbuy.common.pojo.Result;

/**
 * Created by bonismo@hotmail.com
 * 下午11:28 on 16/11/29.
 */
public interface ItemService {

    /**
     * 根据商品 ID 获取商品基本信息
     *
     * @param itemId
     * @return
     */
    Result getItemBaseInfo(long itemId);

    /**
     * 根据商品 ID 获取商品描述信息
     *
     * @param itemId
     * @return
     */
    Result getItemDesc(long itemId);

    /**
     * 根据商品 ID 获取商品规格参数
     *
     * @param itemId
     * @return
     */
    Result getItemParam(long itemId);
}
