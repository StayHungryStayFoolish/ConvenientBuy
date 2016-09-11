package com.convenientbuy.service.impl;

import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午10:49 on 16/9/11.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CbItemMapper cbItemMapper;

    @Override
    public CbItem getItemById(long itemId) {
        return null;
    }
}
