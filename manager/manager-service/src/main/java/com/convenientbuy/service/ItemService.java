package com.convenientbuy.service;

import com.convenientbuy.common.pojo.EUDataGridResult;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbItem;

/**
 * Created by bonismo@hotmail.com
 * 下午10:48 on 16/9/11.
 */
public interface ItemService {

    CbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page, int rows);
    Result createItem(CbItem item, String desc, String itemParam) throws Exception;
}
