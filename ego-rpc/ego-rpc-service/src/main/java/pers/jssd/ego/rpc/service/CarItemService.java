package pers.jssd.ego.rpc.service;

import pers.jssd.ego.rpc.entity.CarItem;

import java.util.Map;

/**
 * 购物车条目服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface CarItemService {
    
    /**
     * 将此商品放入购物车中
     *
     * @param uid    用户id
     * @param itemId 商品id
     */
    void addItemToCar(Long uid, Long itemId);
    
    /**
     * 加载用户购物车列表
     *
     * @param uid 用户id
     * @return 购物车所有商品信息Map
     */
    Map<Long, CarItem> loadCarList(Long uid);
    
    /**
     * 更改购物车中指定商品的数量
     *
     * @param uid    用户id
     * @param itemId 商品id
     * @param num    购物车中更新的数量
     * @return 成功返回"ok"
     */
    String updateCarItemNum(Long uid, Long itemId, Integer num);
    
    /**
     * 删除购物车中指定的商品
     *
     * @param uid    用户id
     * @param itemId 删除的指定商品id
     */
    void deleteCarItem(Long uid, Long itemId);
    
    /**
     * 清空购物车
     *
     * @param uid 用户id
     */
    void deleteCarMap(Long uid);
}
