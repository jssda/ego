package pers.jssd.ego.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.portal.service.PortalContentService;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class PortalContentController {
    
    private final PortalContentService portalContentService;
    
    @Autowired
    public PortalContentController(PortalContentService portalContentService) {
        this.portalContentService = portalContentService;
    }
    
    /**
     * 查询所有此分类下的内容信息
     *
     * @param categoryId 内容的分类id
     * @return 返回内容信息BigPicture转换而成的json串
     */
    @RequestMapping(value = "/content/index/list", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String contentList(Long categoryId) {
        return portalContentService.loadContentByCid(categoryId);
    }
}
