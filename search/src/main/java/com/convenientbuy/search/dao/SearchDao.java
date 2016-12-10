package com.convenientbuy.search.dao;

import com.convenientbuy.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by bonismo@hotmail.com
 * 下午10:28 on 16/12/10.
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
