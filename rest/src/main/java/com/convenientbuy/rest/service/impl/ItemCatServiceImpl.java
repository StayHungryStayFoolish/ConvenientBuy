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

    /**
     * 查询商品分类列表 item.jsp 内 通过 lib-v1.js 调用回调函数
     *
     * @return
     */
    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    /**
     * 查询商品分类列表 item.jsp 内 通过 lib-v1.js 调用回调函数
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
            //判断是否为父节点
            if (cbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + cbItemCat.getId() + ".html'>" + cbItemCat.getName() + "</a>");
                } else {
                    catNode.setName(cbItemCat.getName());
                }
                catNode.setUrl("/products/" + cbItemCat.getId() + ".html");
                catNode.setItem(getCatList(cbItemCat.getId()));

                resultList.add(catNode);
                count++;
                //第一层只取14条记录
                if (parentId == 0 && count >= 14) {
                    break;
                }
                //如果是叶子节点
            } else {
                resultList.add("/products/" + cbItemCat.getId() + ".html|" + cbItemCat.getName());
            }
        }
        return resultList;
    }
}
