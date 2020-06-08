package pers.jssd.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * 使用Solr查询商品的持久层接口, 完成商品的检索
 *
 * @author jssdjing@gmail.com
 */
public interface ItemDao {
    
    /**
     * 完成商品的检索功能
     *
     * @param solrQuery solr检索条件
     * @return 返回solr检索到的数据
     */
    QueryResponse loadItem(SolrQuery solrQuery);
    
}
