package pers.jssd.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.search.service.SearchItemDescService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class SearchItemDescController {
    
    private final SearchItemDescService searchItemDescService;
    
    @Autowired
    public SearchItemDescController(SearchItemDescService searchItemDescService) {
        this.searchItemDescService = searchItemDescService;
    }
    
    /**
     * 通过商品id查询商品的规格信息
     *
     * @param id 商品的id
     * @return 返回商品的规格信息
     */
    @RequestMapping("/item/desc/{id}")
    @ResponseBody
    public String getItemDescById(@PathVariable Long id) {
        return searchItemDescService.loadItemDescService(id);
    }
    
}
