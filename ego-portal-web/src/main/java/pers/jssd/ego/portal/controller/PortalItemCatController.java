package pers.jssd.ego.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.portal.service.PortalItemCatService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class PortalItemCatController {
    
    private final PortalItemCatService portalItemCatService;
    
    @Autowired
    public PortalItemCatController(PortalItemCatService portalItemCatService) {
        this.portalItemCatService = portalItemCatService;
    }
    
    /**
     * 处理商品的分类信息
     * @return 返回前台需要的由CatResult转换而成的json串
     */
    @RequestMapping(value = "/item/cat", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String itemCat() {
        return portalItemCatService.loadItemCat();
    }
}
