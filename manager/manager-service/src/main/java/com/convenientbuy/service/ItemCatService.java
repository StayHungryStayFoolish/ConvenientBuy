package com.convenientbuy.service;

import com.convenientbuy.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:16 on 16/9/15.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}
