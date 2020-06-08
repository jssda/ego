package pers.jssd.ego.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.service.ItemService;
import pers.jssd.ego.search.dao.ItemDao;
import pers.jssd.ego.search.entity.Item;
import pers.jssd.ego.search.entity.SearchResult;
import pers.jssd.ego.search.service.SearchItemService;

import java.util.List;
import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    
    /**
     * solr操作服务注入
     */
    private final ItemDao itemDao;
    
    /**
     * 远程商品服务类注入
     */
    private final ItemService itemService;
    
    @Autowired
    public SearchItemServiceImpl(ItemDao itemDao, ItemService itemService) {
        this.itemDao = itemDao;
        this.itemService = itemService;
    }
    
    @Override
    public SearchResult loadItemService(String item_keywords, Integer page) {
        
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询字段
        solrQuery.set("df", "item_keywords");
        
        // 设置匹配条件
        if (!StringUtils.isEmpty(item_keywords)) {
            solrQuery.setQuery(item_keywords);
        } else {
            solrQuery.set("q", "*:*");
        }
        
        // 维护当前页的健壮性, 不能小于1, 大于所有页数
        if (page < 1) {
            page = 1;
        }
        long maxPage = 100;
        if (page > maxPage) {
            page = Math.toIntExact(maxPage);
        }
        
        // 指定分页参数
        int row = 20;
        Integer start = (page - 1) * row;
        solrQuery.setStart(start);
        solrQuery.setRows(row);
        
        // 设定高亮参数
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("title");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        
        // 查询数据
        QueryResponse queryResponse = itemDao.loadItem(solrQuery);
        // 获得查询到的文档集合
        SolrDocumentList results = queryResponse.getResults();
        // 获取高亮数据
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        // 获得查询到的总记录数
        long total = results.getNumFound();
        // 将solr数据集合转换成商品数据集合
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<Item> beans = binder.getBeans(Item.class, results);
        
        // 处理高亮数据
        for (Item item : beans) {
            String id = item.getId();
            // 取得查询到的此商品高亮数据, 如果没有, 取得null
            Map<String, List<String>> listMap = highlighting.get(id);
            if (listMap != null) {
                // 查看title字段有没有高亮数据, 如果有高亮数据, 设置此商品的title
                List<String> title = listMap.get("title");
                if (title != null && title.size() > 0) {
                    item.setTitle(title.get(0));
                }
            }
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setList(beans);
        searchResult.setMaxPage(maxPage);
        searchResult.setPage(page);
        searchResult.setTotal(total);
        
        return searchResult;
    }
    
    @Override
    public TbItem loadItemService(Long id) {
        return itemService.findItem(id);
    }
}
