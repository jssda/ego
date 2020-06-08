package pers.jssd.ego.rpc.service;

import pers.jssd.ego.rpc.pojo.TbOrder;
import pers.jssd.ego.rpc.pojo.TbOrderItem;
import pers.jssd.ego.rpc.pojo.TbOrderShipping;

import java.util.List;

/**
 * 订单管理远程服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface OrderService {
    
    /**
     * 存储订单信息
     *
     * @param order         用户订单
     * @param orderItems    订单商品信息
     * @param orderShipping 物流信息
     */
    void saveOrderService(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);
    
    /**
     * 加载用户的订单
     *
     * @param uid 用户id
     * @return 返回此用户的全部订单
     */
    List<TbOrder> loadOrderByUid(Long uid);
    
    /**
     * 加载订单信息
     *
     * @param orderId 订单信息
     * @return 返回指定订单的详细信息
     */
    List<TbOrderItem> loadOrderDetail(String orderId);
    
    
}
