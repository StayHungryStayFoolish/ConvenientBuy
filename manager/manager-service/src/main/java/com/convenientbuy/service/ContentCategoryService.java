package com.convenientbuy.service;

import com.convenientbuy.common.pojo.EUTreeNode;
import com.convenientbuy.common.pojo.Result;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午9:51 on 16/8/18.
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    Result insertContentCategory(long parentId, String name);
}
