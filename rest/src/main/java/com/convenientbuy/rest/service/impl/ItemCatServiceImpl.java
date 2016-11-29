package com.convenientbuy.rest.service.impl;

import com.convenientbuy.mapper.CbItemCatMapper;
import com.convenientbuy.pojo.CbItemCat;
import com.convenientbuy.pojo.CbItemCatExample;
import com.convenientbuy.rest.pojo.CatNode;
import com.convenientbuy.rest.pojo.CatResult;
import com.convenientbuy.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:30 on 16/11/29.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private CbItemCatMapper mapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(get);
        return null;
    }

    /**
     * 查询商品分类列表
     *
     * @param parentId
     * @return
     */
    private List<?> getCatList(long parentId) {
        //创建查询条件
        CbItemCatExample example = new CbItemCatExample();
        CbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<CbItemCat> list = mapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        int count = 0;
        for (CbItemCat cbItemCat : list) {

        }
        return resultList;
    }
}
