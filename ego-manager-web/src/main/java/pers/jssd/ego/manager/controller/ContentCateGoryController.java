package pers.jssd.ego.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.TreeNode;
import pers.jssd.ego.manager.service.ManagerContentCategoryService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/content/category")
public class ContentCateGoryController {
    
    private final ManagerContentCategoryService managerContentCategoryService;
    
    public ContentCateGoryController(ManagerContentCategoryService managerContentCategoryService) {
        this.managerContentCategoryService = managerContentCategoryService;
    }
    
    /**
     * 列出所有内容类别请求
     *
     * @param pid 父id
     * @return 返回此父节点下所有的子节点所封装而成的树节点TreeNode
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public List<TreeNode> listContentCategory(@RequestParam(defaultValue = "0", name = "id") Long pid) {
        return managerContentCategoryService.findContentCategory(pid);
    }
    
    /**
     * 添加一个车节点
     *
     * @param parentId 需要添加节点的父节点id
     * @param name 此节点的名字
     * @return 返回响应信息EgoResult
     */
    @RequestMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult listContentCategory(Long parentId, String name) {
        return managerContentCategoryService.saveContentCategory(parentId, name);
    }
    
    /**
     * 更新一个节点
     *
     * @param id 需要更新节点的id
     * @param name 更新之后的名字
     * @return 返回状态响应节点
     */
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult updateContentCategory(Long id, String name) {
        return managerContentCategoryService.updateContentCategory(id, name);
    }
    
    /**
     * 处理删除一个内容分类节点的请求
     *
     * @param id 删除节点的id
     * @return 返回响应信息EgoResult
     */
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult deleteContentCategory(Long id) {
        return managerContentCategoryService.deleteContentCategory(id);
    }
    
    
    
    
}
