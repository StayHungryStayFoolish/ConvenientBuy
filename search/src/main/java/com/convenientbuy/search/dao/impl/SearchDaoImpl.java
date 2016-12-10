package com.convenientbuy.search.dao.impl;

import com.convenientbuy.search.dao.SearchDao;
import com.convenientbuy.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午10:29 on 16/12/10.
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        return null;
    }
}
