package pers.jssd.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.manager.service.ManagerItemParamService;
import pers.jssd.ego.rpc.pojo.TbItemParam;

/**
 * 处理商品属性模板请求
 *
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    private final ManagerItemParamService itemParamService;

    @Autowired
    public ItemParamController(
            ManagerItemParamService itemParamService) {
        this.itemParamService = itemParamService;
    }

    /**
     * 显示商品属性模板信息, 分页显示
     *
     * @param page 当前页
     * @param rows 每页显示多少条数据
     * @return 返回分页对象PageResult
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public PageResult<TbItemParam> itemParamList(Integer page, Integer rows) {
        return itemParamService.findItemParam(page, rows);
    }

    /**
     * 查询商品类目是否已经有属性模板
     *
     * @return 返回查询响应对象EgoResult
     * @param cid 需要查询的商品类目id
     */
    @RequestMapping(value = "/query/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult findItemParam(@PathVariable Long cid) {
        return itemParamService.getItemParamByCid(cid);
    }

    /**
     * 存储商品类目对应的属性模板
     *
     * @param cid 需要查询的商品类目id
     * @param paramData 商品模板信息
     * @return 返回是否存储成功的响应对象EgoResult
     */
    @RequestMapping(value = "/save/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult findItemParam(@PathVariable Long cid, String paramData) {
        return itemParamService.saveItemParam(cid, paramData);
    }

    /**
     * 删除选中的itemParam信息
     *
     * @param ids 需要删除的ItemParam的id数组
     * @return 返回是否删除成功的响应对象EgoResult
     */
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult findItemParam(Long[] ids) {
        return itemParamService.deleteItemParam(ids);
    }
}
