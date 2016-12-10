package com.convenientbuy.search.service.impl;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleUnique;
import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.ExceptionUtil;
import com.convenientbuy.search.mapper.ItemMapper;
import com.convenientbuy.search.pojo.Item;
import com.convenientbuy.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:36 on 16/12/10.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper mapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public Result importAllItems() {
        try {
            // 获取商品列表
            List<Item> list = mapper.getItemList();
            for (Item item : list) {
                // 创建 Solr 导入数据流
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_des());

                // 添加到索引库
                solrServer.add(document);
            }
            // 提交
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, ExceptionUtil.getStackTrace(e));
        }
        return Result.ok();
    }
}
