package com.convenientbuy.search.service.impl;

import com.convenientbuy.search.dao.SearchDao;
import com.convenientbuy.search.pojo.SearchResult;
import com.convenientbuy.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午10:42 on 16/12/10.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        // 创建查询对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
        query.setQuery(queryString);
        // 设置分页，setStart 是从 0 开始的，所以 -1
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        // 设置默认搜素域   df 具体见 solr 启动页面的字段
        query.set("df", "item_keywords");
        // 设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        // em 斜体
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        // 执行查询
        SearchResult searchResult = searchDao.search(query);
        // 计算查询结果总页数
        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount / rows;
        // 如果有余数，页数 +1
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);

        return searchResult;
    }
}
