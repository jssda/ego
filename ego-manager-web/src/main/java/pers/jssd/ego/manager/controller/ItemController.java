package pers.jssd.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.manager.service.ManagerItemService;
import pers.jssd.ego.rpc.pojo.TbItem;

/**
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private final ManagerItemService itemService;

    /**
     * 构造方式注入服务对象
     */
    @Autowired
    public ItemController(ManagerItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 分页展示商品
     *
     * @param page 当前页
     * @param rows 每页多少条数据
     * @return 分页数据PageResult所转换的json对象
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public PageResult<TbItem> itemList(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "30") Integer rows) {
        return itemService.findItem(page, rows);
    }

    /**
     * 上架商品
     *
     * @return 响应对象EgoResult所转换的json对象
     */
    @RequestMapping(value = "/reshelf", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult reshelfItem(Long[] ids) {
        return this.itemService.reshelfItem(ids);
    }

    /**
     * 下架商品
     *
     * @return 响应对象EgoResult所转换的json对象
     */
    @RequestMapping(value = "/instock", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult instockItem(Long[] ids) {
        return this.itemService.instockItem(ids);
    }

    /**
     * 删除商品部信息
     *
     * @param ids 删除商品的id
     * @return 返回删除商品是否成功的响应对象
     */
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult removeItem(Long[] ids) {
        return this.itemService.removeItem(ids);
    }


    /**
     * 添加商品信息
     *
     * @param tbItem 需要添加的商品信息
     * @param desc   商品描述
     * @param itemParams 商品规格参数信息
     * @return 返回EgoResult响应对象
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult saveItem(TbItem tbItem, String desc, String itemParams) {
        return this.itemService.saveItem(tbItem, desc, itemParams);
    }

    /**
     * 更新商品信息
     *
     * @param tbItem 需要添加的商品信息
     * @param desc   商品描述
     * @param itemParams 商品的规格参数信息
     * @return 返回EgoResult响应对象
     */
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult updateItem(TbItem tbItem, String desc, String itemParams) {
        return this.itemService.updateItem(tbItem, desc, itemParams);
    }
}
