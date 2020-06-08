package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.TreeNode;
import pers.jssd.ego.manager.service.ManagerContentCategoryService;
import pers.jssd.ego.rpc.pojo.TbContentCategory;
import pers.jssd.ego.rpc.service.ContentCategoryService;
import pers.jssd.ego.utils.IDUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {
    
    /**
     * 内容类别远程服务类
     */
    private final ContentCategoryService contentCategoryService;
    
    @Autowired
    public ManagerContentCategoryServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ContentCategoryService contentCategoryService) {
        this.contentCategoryService = contentCategoryService;
    }
    
    @Override
    public List<TreeNode> findContentCategory(Long pid) {
        List<TreeNode> list = new ArrayList<>();
        List<TbContentCategory> contentCategory = contentCategoryService.findContentCategory(pid);
        for (TbContentCategory category : contentCategory) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            treeNode.setState(category.getIsParent() ? "closed" : "open");
            
            list.add(treeNode);
        }
        
        return list;
    }
    
    
    @Override
    public EgoResult saveContentCategory(Long parentId, String name) {
        // 需要添加的对象
        TbContentCategory contentCategory = new TbContentCategory();
        // 对添加的对象数据封装
        Date date = new Date();
        contentCategory.setId(IDUtils.genItemId());
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        
        return contentCategoryService.saveContentCategory(contentCategory);
    }
    
    @Override
    public EgoResult updateContentCategory(Long id, String name) {
        
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategory.setUpdated(new Date());
        return contentCategoryService.updateContentCategory(contentCategory);
    }
    
    @Override
    public EgoResult deleteContentCategory(Long id) {
        
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        
        return contentCategoryService.deleteContentCategory(contentCategory);
    }
}
