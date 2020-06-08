package pers.jssd.ego.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.rpc.service.CarItemService;
import pers.jssd.ego.rpc.entity.CarItem;
import pers.jssd.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 购物车请求处理器
 *
 * @author jssdjing@gmail.com
 */
@Controller
public class CarItemController {
    
    private final CarItemService carItemService;
    
    @Autowired
    public CarItemController(CarItemService carItemService) {
        this.carItemService = carItemService;
    }
    
    /**
     * 将商品添加到购物车
     *
     * @param itemId 商品的id
     * @return 跳转到添加商品成功的页面
     */
    @RequestMapping("/cart/add/{itemId}")
    public String cartAdd(@PathVariable Long itemId, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        carItemService.addItemToCar(uid, itemId);
        return "cartSuccess";
    }
    
    /**
     * 加载购物车商品集合
     */
    @RequestMapping("/cart/cart")
    public String loadCatItemList(HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        Map<Long, CarItem> longCarItemMap = carItemService.loadCarList(uid);
        request.setAttribute("carMap", longCarItemMap);
        return "cart";
    }
    
    /**
     * 更新购物车的商品数量
     *
     * @param itemId  商品id
     * @param num     购物数量
     * @param request 请求
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public String cartUpdateNum(@PathVariable Long itemId,
                                @PathVariable Integer num, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        return carItemService.updateCarItemNum(uid, itemId, num);
    }
    
    /**
     * 删除购物车中指定的商品
     *
     * @param itemId 商品id
     * @return 删除成功之后, 重定向到购物车页面
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String removeCarItem(@PathVariable Long itemId, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        carItemService.deleteCarItem(uid, itemId);
        return "redirect:/cart/cart.html";
    }
    
    /**
     * 删除指定用户的全部商品
     */
    @RequestMapping("/delete/cart/all")
    public String removeCarMap(HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        carItemService.deleteCarMap(uid);
        return "redirect:/cart/cart.html";
    }
}
