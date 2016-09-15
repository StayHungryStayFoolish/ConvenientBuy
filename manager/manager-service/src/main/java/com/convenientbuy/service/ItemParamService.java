package com.convenientbuy.service;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbItemParam;

/**
 * Created by bonismo@hotmail.com
 * 下午11:15 on 16/9/15.
 */
public interface ItemParamService {

    Result getItemParamByCid(long cid);
    Result insertItemParam(CbItemParam itemParam);
}
