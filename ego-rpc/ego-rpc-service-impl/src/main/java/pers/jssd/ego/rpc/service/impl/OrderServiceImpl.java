package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.mapper.TbOrderItemMapper;
import pers.jssd.ego.rpc.mapper.TbOrderMapper;
import pers.jssd.ego.rpc.mapper.TbOrderShippingMapper;
import pers.jssd.ego.rpc.pojo.*;
import pers.jssd.ego.rpc.service.OrderService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    private final TbOrderMapper orderMapper;
    private final TbOrderItemMapper orderItemMapper;
    private final TbOrderShippingMapper orderShippingMapper;
    
    @Autowired
    public OrderServiceImpl(TbOrderMapper orderMapper, TbOrderItemMapper orderItemMapper,
                            TbOrderShippingMapper orderShippingMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderShippingMapper = orderShippingMapper;
    }
    
    @Override
    public void saveOrderService(TbOrder order, List<TbOrderItem> orderItems,
                                 TbOrderShipping orderShipping) {
        orderMapper.insert(order);
        for (TbOrderItem orderItem : orderItems) {
            orderItemMapper.insert(orderItem);
        }
        orderShippingMapper.insert(orderShipping);
    }
    
    @Override
    public List<TbOrder> loadOrderByUid(Long uid) {
        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria orderExampleCriteria = orderExample.createCriteria();
        orderExampleCriteria.andUserIdEqualTo(uid);
        
        return orderMapper.selectByExample(orderExample);
    }
    
    @Override
    public List<TbOrderItem> loadOrderDetail(String orderId) {
        
        TbOrderItemExample orderItemExample = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return orderItemMapper.selectByExample(orderItemExample);
    }
    
}
