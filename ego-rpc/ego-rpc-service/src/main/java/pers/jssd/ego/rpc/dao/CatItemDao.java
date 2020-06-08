package pers.jssd.ego.rpc.dao;

import java.util.Map;

/**
 * 购物车信息存储在redis数据库中. 此接口为购物车操作redis数据库接口
 *
 * @author jssdjing@gmail.com
 */
public interface CatItemDao {
    
    /**
     * 将购物车信息存储到redis中. 作为hash对象存储. key为用户id. value为购物车map
     *
     * @param uid 用户id
     * @param map 购物车map对象, map的key为商品id, value为购物车对象CarItem
     */
    void setCarMap(String uid, Map<String, String> map);
    
    /**
     * 更新或设置redis数据库中hash表中购物车项
     *
     * @param uid        用户id
     * @param itemId     商品id
     * @param carItemStr 更新之后的商品项
     */
    void setCarItem(String uid, String itemId, String carItemStr);
    
    /**
     * 在redis数据库中取得指定用户对应得购物车中得商品
     *
     * @param uid 用户得id
     * @return 返回此用户的购物车商品信息
     */
    Map<String, String> getCarMap(String uid);
    
    /**
     * 取得指定用户指定商品的商品信息
     *
     * @param uid    用户id
     * @param itemId 商品id
     * @return 返回取得的指定商品的carItem信息. 返回一个json串
     */
    String getCarItemInfo(String uid, String itemId);
    
    /**
     * 购物车商品删除
     *
     * @param uid 用户id
     * @param itemId 商品id
     */
    void deleteCarItem(String uid, String itemId);
    
    
    /**
     * 清空指定用户的购物车
     * @param uid 用户id
     */
    void deleteCarMap(String uid);
    
}
