package pers.jssd.ego.manager.service.impl;

import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.TreeNode;
import pers.jssd.ego.manager.service.ManagerItemCatService;
import pers.jssd.ego.rpc.pojo.TbItemCat;
import pers.jssd.ego.rpc.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

    private final ItemCatService itemCatService;

    public ManagerItemCatServiceImpl(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    @Override
    public List<TreeNode> getTreeNode(Long id) {
        List<TbItemCat> itemCats = itemCatService.getItemCatByParentId(id);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TbItemCat itemCat : itemCats) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() ? "closed" : "open");
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }
}
