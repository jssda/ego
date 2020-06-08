package pers.jssd.ego.order.service;

import pers.jssd.ego.rpc.entity.CarItem;
import pers.jssd.ego.rpc.pojo.TbOrder;
import pers.jssd.ego.rpc.pojo.TbOrderItem;
import pers.jssd.ego.rpc.pojo.TbOrderShipping;

import java.util.List;
import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
public interface OrderService {
    
    /**
     * 加载用户购物车集合
     *
     * @param uid 用户的id
     * @return 购物车集合
     */
    Map<Long, CarItem> loadItemCar(Long uid);
    
    /**
     * 存储用户订单信息
     *
     * @param order    用户的订单信息
     * @param uid      用户id
     * @param shipping 购物车信息
     * @return 返回请求参数信息 orderId和total
     */
    Map<String, String> saveOrder(TbOrder order, Long uid, TbOrderShipping shipping);
    
    /**
     * 加载用户的订单集合
     *
     * @param uid 用户的id
     * @return 此用户的订单集合
     */
    List<TbOrder> loadOrder(Long uid);
    
    /**
     * 加载订单的详细信息
     *
     * @param orderId 订单的id
     */
    List<TbOrderItem> loadOrderItem(String orderId);
}
