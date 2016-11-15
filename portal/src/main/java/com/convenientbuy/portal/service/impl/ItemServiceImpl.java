package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.pojo.CbItemDesc;
import com.convenientbuy.pojo.CbItemParamItem;
import com.convenientbuy.portal.pojo.ItemInfo;
import com.convenientbuy.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据商品 ID 获取商品规格参数信息
     *
     * @param itemId
     * @return
     */
    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            Result result = Result.formatToPojo(json, CbItemParamItem.class);
            if (result.getStatus() == 200) {
                CbItemParamItem itemParamItem = (CbItemParamItem) result.getData();
                String paramData = itemParamItem.getParamData();
                // 将规格参数数据转换成对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer buffer = new StringBuffer();
                buffer.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                buffer.append("    <tbody>\n");
                for (Map m1 : jsonList) {
                    buffer.append("        <tr>\n");
                    buffer.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
                    buffer.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for (Map m2 : list2) {
                        buffer.append("        <tr>\n");
                        buffer.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                        buffer.append("            <td>" + m2.get("v") + "</td>\n");
                        buffer.append("        </tr>\n");
                    }
                }
                buffer.append("    </tbody>\n");
                buffer.append("</table>");
                //返回html片段
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
