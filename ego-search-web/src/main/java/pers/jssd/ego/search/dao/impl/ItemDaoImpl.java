package pers.jssd.ego.search.dao.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.jssd.ego.search.dao.ItemDao;

/**
 * @author jssdjing@gmail.com
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    
    /**
     * solr客户端对象
     */
    private final CloudSolrServer solrServer;
    
    @Autowired
    public ItemDaoImpl(CloudSolrServer solrServer) {
        this.solrServer = solrServer;
    }
    
    @Override
    public QueryResponse loadItem(SolrQuery solrQuery) {
    
        try {
            return solrServer.query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
