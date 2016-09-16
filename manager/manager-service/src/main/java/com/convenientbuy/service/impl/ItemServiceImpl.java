package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.EUDataGridResult;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.pojo.CbItemExample;
import com.convenientbuy.service.ItemService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.convenientbuy.pojo.CbItemParamItemExample.Criteria;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:49 on 16/9/11.
 */
@Service
//@MapperScan("com.convenientbuy.mapper")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CbItemMapper mapper;

    @Override
    public CbItem getItemById(long itemId) {
        System.out.println(" 进入方法 ...");
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

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        return null;
    }

    @Override
    public Result createItem(CbItem item, String desc, String itemParam) throws Exception {
        return null;
    }
}
