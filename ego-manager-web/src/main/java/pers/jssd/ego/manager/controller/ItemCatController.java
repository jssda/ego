package pers.jssd.ego.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.TreeNode;
import pers.jssd.ego.manager.service.ManagerItemCatService;

import java.util.List;

/**
 * 商品类目网络请求接口
 *
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    private final ManagerItemCatService itemCatService;

    public ItemCatController(ManagerItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public List<TreeNode> getChildrenNode(@RequestParam(defaultValue = "0", required = false) Long id) {
        return itemCatService.getTreeNode(id);
    }

}
