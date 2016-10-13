package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbItemParamMapper;
import com.convenientbuy.pojo.CbItemParam;
import com.convenientbuy.pojo.CbItemParamExample;
import com.convenientbuy.service.ItemParamService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午11:36 on 16/9/15.
 */
@Service
@MapperScan("com.convenientbuy.mapper")
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private CbItemParamMapper mapper;

    /**
     * 根据 ID 查询商品规格参数
     * @param cid
     * @return
     */
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

    /**
     * 添加商品规格参数
     * @param itemParam
     * @return
     */
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
