package pers.jssd.ego.search.service;

/**
 * 商品规格服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface SearchItemParamService {
    
    /**
     * 查询商品的规格信息
     *
     * @param id 商品的id
     * @return 返回商品的规格信息
     */
    String loadItemParamService(Long id);
    
}
