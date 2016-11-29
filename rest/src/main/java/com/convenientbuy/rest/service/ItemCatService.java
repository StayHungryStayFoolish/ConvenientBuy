package com.convenientbuy.rest.service;

import com.convenientbuy.rest.pojo.CatResult;

/**
 * Created by bonismo@hotmail.com
 * 下午11:26 on 16/11/29.
 */
public interface ItemCatService {

    /**
     * 获取商品分类列表
     * @return
     */
    CatResult getItemCatList();
}
