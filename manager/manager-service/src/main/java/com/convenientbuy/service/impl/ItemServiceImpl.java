package com.convenientbuy.service.impl;

import com.convenientbuy.common.pojo.EUDataGridResult;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.IDUtils;
import com.convenientbuy.mapper.CbItemDescMapper;
import com.convenientbuy.mapper.CbItemMapper;
import com.convenientbuy.mapper.CbItemParamItemMapper;
import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.pojo.CbItemDesc;
import com.convenientbuy.pojo.CbItemExample;
import com.convenientbuy.pojo.CbItemParamItem;
import com.convenientbuy.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:49 on 16/9/11.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CbItemMapper mapper;

    @Autowired
    private CbItemDescMapper itemDescMapper;

    @Autowired
    private CbItemParamItemMapper cbItemParamItemMapper;

    @Override
    public CbItem getItemById(long itemId) {
        System.out.println(" 进入方法 ...");
        //添加查询条件
        CbItemExample example = new CbItemExample();
        CbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<CbItem> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            CbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        CbItemExample example = new CbItemExample();
        PageHelper.startPage(page, rows);
        List<CbItem> list = mapper.selectByExample(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<CbItem> pageInfo = new PageInfo(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 添加商品
     * @param item 商品
     * @param desc 描述
     * @param itemParam 商品规格
     * @return
     * @throws Exception
     */
    @Override
    public Result createItem(CbItem item, String desc, String itemParam) throws Exception {
        Long itemId = IDUtils.getItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        mapper.insert(item);
        //添加商品描述信息
        Result result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        return Result.ok();
    }

    /**
     * 添加商品描述
     * @param itemId
     * @param desc
     * @return
     */
    private Result insertItemDesc(Long itemId, String desc) {
        CbItemDesc itemDesc = new CbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return Result.ok();
    }

    /**
     * 添加规格参数
     * @param itemId
     * @param itemParam
     * @return
     */
    private Result insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        CbItemParamItem itemParamItem = new CbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        cbItemParamItemMapper.insert(itemParamItem);

        return Result.ok();

    }
}
