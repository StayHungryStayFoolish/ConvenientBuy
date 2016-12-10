package com.convenientbuy.search.service;

import com.convenientbuy.common.pojo.Result;

/**
 * Created by bonismo@hotmail.com
 * 下午10:35 on 16/12/10.
 */
public interface ItemService {

    /**
     * 导入索引库接口
     *
     * @return
     */
    Result importAllItems();
}
