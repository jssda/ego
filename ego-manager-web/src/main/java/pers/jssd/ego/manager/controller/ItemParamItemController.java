package pers.jssd.ego.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.manager.service.ManagerItemParamItemService;
import pers.jssd.ego.rpc.service.ItemParamItemService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class ItemParamItemController {

    private final ManagerItemParamItemService itemParamItemService;

    public ItemParamItemController(ManagerItemParamItemService itemParamItemService) {
        this.itemParamItemService = itemParamItemService;
    }

    @RequestMapping(value = "/item/param/item/query/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE +
            ";charset=utf-8")
    @ResponseBody
    public EgoResult getItemParamItemById(@PathVariable Long itemId) {
        return itemParamItemService.findItemParamItemById(itemId);
    }

}
