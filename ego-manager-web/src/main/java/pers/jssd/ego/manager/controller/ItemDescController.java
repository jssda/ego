package pers.jssd.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.manager.service.ManagerItemDescService;

/**
 * 响应商品信息的描述回显请求
 *
 * @author jssdjing@gmail.com
 */
@Controller
public class ItemDescController {

    private final ManagerItemDescService itemDescService;

    public ItemDescController(ManagerItemDescService itemDescService) {
        this.itemDescService = itemDescService;
    }

    @RequestMapping(value = "/item/query/item/desc/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult getItemDesc(@PathVariable Long itemId) {
        return itemDescService.getItemDesc(itemId);
    }

}
