package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbItemParamMapper;
import com.convenientbuy.pojo.CbItemParam;
import com.convenientbuy.pojo.CbItemParamExample;
import com.convenientbuy.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:36 on 16/9/15.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private CbItemParamMapper mapper;

    @Override
    public Result getItemParamByCid(long cid) {
        CbItemParamExample example = new CbItemParamExample();
        CbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<CbItemParam> list = mapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return Result.ok(list.get(0));
        }

        return Result.ok();
    }

    @Override
    public Result insertItemParam(CbItemParam itemParam) {
        //补全pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入到规格参数模板表
        mapper.insert(itemParam);
        return Result.ok();
    }
}
