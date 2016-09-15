package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.EUTreeNode;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbContentCategoryMapper;
import com.convenientbuy.pojo.CbContentCategory;
import com.convenientbuy.pojo.CbContentCategoryExample;
import com.convenientbuy.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:02 on 16/9/15.
 */
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CbContentCategoryMapper mapper;

    /**
     * CMS 获取列表内容
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentid查询节点列表
        CbContentCategoryExample example = new CbContentCategoryExample();
        CbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<CbContentCategory> list = mapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (CbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");

            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public Result insertContentCategory(long parentId, String name) {
        //创建一个pojo
        CbContentCategory contentCategory = new CbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        mapper.insert(contentCategory);
        //查看父节点的isParent列是否为true，如果不是true改成true
        CbContentCategory parentCat = mapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            mapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return Result.ok(contentCategory);
    }
}
