package pers.jssd.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.search.entity.SearchResult;
import pers.jssd.ego.search.service.SearchItemService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class SearchItemController {
    
    private final SearchItemService searchItemService;
    
    @Autowired
    public SearchItemController(SearchItemService searchItemService) {
        this.searchItemService = searchItemService;
    }
    
    /**
     * 通过关键字查询商品
     *
     * @param search 请求路径
     * @param q      商品的检索关键字
     * @param page   当前页
     * @return 返回跳转的页面
     */
    @RequestMapping(value = "/{search}")
    public String searchItem(@PathVariable String search, String q, @RequestParam(defaultValue = "1") Integer page, Model model) {
        
        try {
            SearchResult searchResult = searchItemService.loadItemService(q, page);
            model.addAttribute("query", q);
            model.addAttribute("itemList", searchResult.getList());
            model.addAttribute("page", page);
            model.addAttribute("maxpage", searchResult.getMaxPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return search;
    }
    
    
    @RequestMapping(value = "/item/{id}")
    public String loadItem(@PathVariable Long id, Model model) {
        
        TbItem tbItem = searchItemService.loadItemService(id);
        model.addAttribute("item", tbItem);
        
        return "item";
    }
    
}
