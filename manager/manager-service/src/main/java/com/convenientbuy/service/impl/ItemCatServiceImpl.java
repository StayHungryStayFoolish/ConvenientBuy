package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.EUTreeNode;
import com.convenientbuy.mapper.CbItemCatMapper;
import com.convenientbuy.pojo.CbItemCat;
import com.convenientbuy.pojo.CbItemCatExample;
import com.convenientbuy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:16 on 16/9/15.
 */
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private CbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(long parentId) {
        //创建查询条件
        CbItemCatExample example = new CbItemCatExample();
        CbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //根据条件查询
        List<CbItemCat> list = itemCatMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        //把列表转换成treeNodelist
        for (CbItemCat cbItemCat : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(cbItemCat.getId());
            node.setText(cbItemCat.getName());
            node.setState(cbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        //返回结果
        return resultList;
    }
}
