package com.convenientbuy.rest.service;

import com.convenientbuy.pojo.CbContent;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午7:53 on 16/11/8.
 */
public interface ContentService {

    List<CbContent> getContentList(long contentId);
}
