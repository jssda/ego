package pers.jssd.ego.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.order.service.OrderService;
import pers.jssd.ego.rpc.entity.CarItem;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.pojo.TbOrder;
import pers.jssd.ego.rpc.pojo.TbOrderItem;
import pers.jssd.ego.rpc.pojo.TbOrderShipping;
import pers.jssd.ego.rpc.service.CarItemService;
import pers.jssd.ego.utils.IDUtils;

import java.util.*;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    /**
     * 远程redis数据库操作服务代理类
     */
    private final CarItemService carItemService;
    /**
     * 操作用户订单的远程服务代理类
     */
    private final pers.jssd.ego.rpc.service.OrderService orderService;
    
    @Autowired
    public OrderServiceImpl(CarItemService carItemService, pers.jssd.ego.rpc.service.OrderService orderService) {
        this.carItemService = carItemService;
        this.orderService = orderService;
    }
    
    @Override
    public Map<Long, CarItem> loadItemCar(Long uid) {
        return carItemService.loadCarList(uid);
    }
    
    @Override
    public Map<String, String> saveOrder(TbOrder order, Long uid, TbOrderShipping shipping) {
        // 封装购物订单信息
        Date date = new Date();
        String orderId = String.valueOf(IDUtils.genItemId());
        order.setOrderId(orderId);
        order.setPostFee("233");
        order.setStatus(1);
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setShippingName("EMS");
        order.setShippingCode("110120119");
        order.setUserId(uid);
        order.setBuyerMessage("没有留言");
        order.setBuyerNick("jssd");
        order.setBuyerRate(0);
        
        // 从redis数据库中查询指定用户的购物车信息, 将购物车中每条商品的信息抽选出来, 处理成TbOrderItem对象
        // 将购物车信息, 转换为TbOrderItem的集合
        Map<Long, CarItem> itemMap = carItemService.loadCarList(uid);
        List<TbOrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<Long, CarItem> entry : itemMap.entrySet()) {
            CarItem value = entry.getValue();
            TbItem item = value.getItem();
            Integer num = value.getNum();
            TbOrderItem orderItem = new TbOrderItem();
            orderItem.setId(String.valueOf(IDUtils.genItemId()));
            orderItem.setItemId(String.valueOf(item.getId()));
            orderItem.setOrderId(order.getOrderId());
            orderItem.setNum(num);
            orderItem.setTitle(item.getTitle());
            orderItem.setPrice(item.getPrice());
            orderItem.setTotalFee(item.getPrice() * num);
            orderItem.setPicPath(item.getImage());
            orderItems.add(orderItem);
        }
        
        // 封装物流信息
        shipping.setOrderId(order.getOrderId());
        shipping.setReceiverPhone("1110");
        shipping.setCreated(date);
        shipping.setUpdated(date);
        
        // 调用rpc远程服务存储订单信息
        orderService.saveOrderService(order, orderItems, shipping);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        map.put("total", order.getPayment());
        
        // 更新购物车信息
        carItemService.deleteCarMap(uid);
        
        return map;
    }
    
    @Override
    public List<TbOrder> loadOrder(Long uid) {
        return orderService.loadOrderByUid(uid);
    }
    
    @Override
    public List<TbOrderItem> loadOrderItem(String orderId) {
        return orderService.loadOrderDetail(orderId);
    }
}
