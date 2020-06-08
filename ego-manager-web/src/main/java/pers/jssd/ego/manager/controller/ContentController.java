package pers.jssd.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.manager.service.ManagerContentService;
import pers.jssd.ego.rpc.pojo.TbContent;

/**
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    
    private final ManagerContentService managerContentService;
    
    @Autowired
    public ContentController(ManagerContentService managerContentService) {
        this.managerContentService = managerContentService;
    }
    
    /**
     * 显示指定分类信息的所有内容信息
     *
     * @param categoryId 分类信息
     * @param page       当前页
     * @param rows       每页显示多少条数据
     * @return 返回分页数据PageResult
     */
    @RequestMapping(value = "/query/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public PageResult<TbContent> findContent(Long categoryId, @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "20") Integer rows) {
    
        return managerContentService.findContent(categoryId, page, rows);
    }
    
    /**
     * 添加内容
     *
     * @param content 添加的内容
     * @return 返回响应信息EgoResult
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult saveContent(TbContent content) {
    
        return managerContentService.addContent(content);
    }
    
    
    /**
     * 更新内容信息
     *
     * @param content 需要更新的内容信息
     * @return 返回响应信息EgoResult
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult editContent(TbContent content) {
    
        return managerContentService.updateContent(content);
    }
    
    
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult deleteContent(Long[] ids) {
    
        return managerContentService.deleteContent(ids);
    }
    
    
    
    
}
