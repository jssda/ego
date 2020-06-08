package pers.jssd.ego.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.jssd.ego.order.service.OrderService;
import pers.jssd.ego.rpc.entity.CarItem;
import pers.jssd.ego.rpc.pojo.TbOrder;
import pers.jssd.ego.rpc.pojo.TbOrderItem;
import pers.jssd.ego.rpc.pojo.TbOrderShipping;
import pers.jssd.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class OrderController {
    
    private final OrderService orderService;
    
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    /**
     * 订单确认页面
     */
    @RequestMapping("/order/cart")
    String orderCart(HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        
        Map<Long, CarItem> map = orderService.loadItemCar(uid);
        request.setAttribute("carMap", map);
        return "ordercart";
    }
    
    /**
     * 存储用户的订单
     *
     * @param order         用户的订单信息
     * @param orderShipping 用户的路由信息
     * @return 返回一个存放订单号和总结额的map
     */
    @RequestMapping("/order/save")
    String orderSave(TbOrder order, TbOrderShipping orderShipping, HttpServletRequest request) {
        // 获得当前登录的对象
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        Map<String, String> map = orderService.saveOrder(order, uid, orderShipping);
        
        request.setAttribute("orderId", map.get("orderId"));
        request.setAttribute("total", map.get("total"));
        
        return "success";
    }
    
    /**
     * 展现用户的所有的订单信息
     */
    @RequestMapping("/order/list")
    String orderList(HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        List<TbOrder> tbOrders = orderService.loadOrder(uid);
        request.setAttribute("orderList", tbOrders);
        return "orders";
    }
    
    /**
     * 展示用户的订单详细信息
     *
     * @param orderId 订单id
     */
    @RequestMapping("/order/detail/list/{orderId}")
    public String orderDetailList(@PathVariable String orderId, Model model) {
        List<TbOrderItem> list = orderService.loadOrderItem(orderId);
        model.addAttribute("list", list);
        return "ordersdetail";
    }
    
}
