package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.TreeNode;

import java.util.List;

/**
 * 后台内容分类管理服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerContentCategoryService {
    
    /**
     * 查询此父id所有的子节点数据
     *
     * @param pid 父id
     * @return 返回子数据封装的树节点集合
     */
    List<TreeNode> findContentCategory(Long pid);
    
    /**
     * 存储一个节点, 此节点的父节点为parentId, 此节点的名字为name
     *
     * @param parentId 此节点的父节点id
     * @param name 此节点的text字段
     * @return 返回是否存储成功的响应对象EgoResult
     */
    EgoResult saveContentCategory(Long parentId, String name);
    
    /**
     * 更改一个节点状态
     *
     * @param id 需要更改节点的id
     * @param name 更改之后的姓名
     * @return 返回响应状态信息EgoResult
     */
    EgoResult updateContentCategory(Long id, String name);
    
    /**
     * 删除一个节点, 并且如果它的父节点没有其他子元素, 修改其父节点的isParent字段状态
     *
     * @param id 此节点id
     * @return 返回响应信息EgoResult
     */
    EgoResult deleteContentCategory(Long id);
    
}
