package pers.jssd.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.search.service.SearchItemParamService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class SearchItemParamItemController {
    
    private final SearchItemParamService searchItemParamService;
    
    @Autowired
    public SearchItemParamItemController(SearchItemParamService searchItemParamService) {
        this.searchItemParamService = searchItemParamService;
    }
    
    @RequestMapping(value = "/item/param/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String loadItemParamService(@PathVariable Long id) {
        return searchItemParamService.loadItemParamService(id);
    }
    
}
