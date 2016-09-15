package com.convenientbuy.service.impl;

import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.pojo.CbItemExample;
import com.convenientbuy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:49 on 16/9/11.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CbItemMapper mapper;

    @Override
    public CbItem getItemById(long itemId) {
        //添加查询条件
        CbItemExample example = new CbItemExample();
        CbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<CbItem> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            CbItem item = list.get(0);
            return item;
        }
        return null;
    }
}
