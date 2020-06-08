package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.TreeNode;

import java.util.List;

/**
 * 商品类目服务层操作
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerItemCatService {

    /**
     * 通过id， 获得此树节点的所有子节点
     * @param id 此节点的id
     * @return 返回查询到的所有此节点的子节点
     */
    List<TreeNode> getTreeNode(Long id);

}
