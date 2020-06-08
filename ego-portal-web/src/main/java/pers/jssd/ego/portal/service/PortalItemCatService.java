package pers.jssd.ego.portal.service;

/**
 * 门户网站商品类目服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface PortalItemCatService {
    
    /**
     * 加载前台商品类目
     *
     * @return 返回一个以CatResult转换成的json字符串
     */
    String loadItemCat();

}
