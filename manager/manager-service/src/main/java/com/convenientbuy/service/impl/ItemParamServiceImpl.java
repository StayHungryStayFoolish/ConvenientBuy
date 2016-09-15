package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbItemParam;
import com.convenientbuy.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午11:36 on 16/9/15.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired

    @Override
    public Result getItemParamByCid(long cid) {
        return null;
    }

    @Override
    public Result insertItemParam(CbItemParam itemParam) {
        return null;
    }
}
