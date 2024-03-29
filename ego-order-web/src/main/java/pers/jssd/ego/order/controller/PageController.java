package pers.jssd.ego.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class PageController {
    
    @RequestMapping("/{path}")
    public String showPage(@PathVariable String path) {
        return path;
    }
    
    @RequestMapping("/order/order-cart")
    public String showOrder() {
        return "ordercart";
    }
    
}
