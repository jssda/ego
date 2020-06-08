package pers.jssd.ego.search.service;

/**
 * 搜素展现商品介绍信息服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface SearchItemDescService {
    
    /**
     * 加载商品规格描述新型
     *
     * @param id 商品的id
     * @return 返回查找到的商品规格描述信息
     */
    String loadItemDescService(Long id);
    
}
