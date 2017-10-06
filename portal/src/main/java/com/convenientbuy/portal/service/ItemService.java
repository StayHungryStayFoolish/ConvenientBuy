package com.convenientbuy.portal.service;

import com.convenientbuy.portal.pojo.ItemInfo;

/**
 * Created by bonismo@hotmail.com
 * 下午9:43 on 17/10/6.
 */
public interface ItemService {

    /**
     * 根据商品 ID 获取基本信息
     *
     * @param itemId
     * @return
     */
    ItemInfo getItemById(Long itemId);

    /**
     * 根据商品 ID 获取描述信息
     *
     * @param itemId
     * @return
     */
    String getItemDescById(Long itemId);

    /**
     * 根据商品 ID 获取规格参数
     *
     * @param itemId
     * @return
     */
    String getItemParam(Long itemId);

}
