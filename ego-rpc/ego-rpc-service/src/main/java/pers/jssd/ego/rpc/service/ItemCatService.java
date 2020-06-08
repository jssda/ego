package pers.jssd.ego.rpc.service;

import pers.jssd.ego.rpc.pojo.TbItemCat;

import java.util.List;

/**
 * 商品类目服务类
 *
 * @author jssdjing@gmail.com
 */
public interface ItemCatService {

    /**
     * 通过parentId获取所有子元素
     * @param pid 需要查询的parentId
     * @return 返回所有父节点id是Pid的子节点
     */
    List<TbItemCat> getItemCatByParentId(Long pid);
    
    /**
     * 获取所有商品类目信息
     *
     * @return 返回查询到的所有商品类目信息
     */
    List<TbItemCat> findItemCat();
}
