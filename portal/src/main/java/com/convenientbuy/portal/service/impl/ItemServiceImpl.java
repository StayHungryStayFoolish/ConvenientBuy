package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.pojo.CbItemDesc;
import com.convenientbuy.portal.pojo.ItemInfo;
import com.convenientbuy.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午10:38 on 16/11/14.
 */
@Service
public class ItemServiceImpl implements ItemService {

    // Rest 服务层基础 URL
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    // 商品基本信息 URL
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    // 商品描述 URL
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    // 商品规格参数 URL
    private String ITEM_PARAM_URL;

    /**
     * 根据商品 ID 查询基本信息
     *
     * @param itemId
     * @return
     */
    @Override
    public ItemInfo getItemById(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                Result result = Result.formatToPojo(json, ItemInfo.class);
                if (result.getStatus() == 200) {
                    ItemInfo info = (ItemInfo) result.getData();
                    return info;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据商品 ID 获取商品描述数据
     *
     * @param itemId
     * @return
     */
    @Override
    public String getItemDescById(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            Result result = Result.formatToPojo(json, CbItemDesc.class);
            if (result.getStatus() == 200) {
                CbItemDesc itemDesc = (CbItemDesc) result.getData();
                String resultDesc = itemDesc.getItemDesc();
                return resultDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(Long itemId) {
        return null;
    }
}
