package pers.jssd.ego.search.service;

import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.search.entity.SearchResult;

/**
 * 查询商品服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface SearchItemService {
    
    /**
     * 商品信息关键字查询, 并分页
     *
     * @param item_keywords 查询的关键字
     * @param page          查询的当前页
     * @return 返回查询到的响应到前台的检索信息
     */
    SearchResult loadItemService(String item_keywords, Integer page);
    
    /**
     * 通过id查询商品
     *
     * @param id 商品的id
     * @return 返回查询到的商品
     */
    TbItem loadItemService(Long id);
    
}
