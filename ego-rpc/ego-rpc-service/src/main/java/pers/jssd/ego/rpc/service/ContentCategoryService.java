package pers.jssd.ego.rpc.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.rpc.pojo.TbContentCategory;

import java.util.List;

/**
 * 内容分类管理服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ContentCategoryService {
    
    /**
     * 通过父id查询所有此父id所有的子节点内容分类数据
     *
     * @param pid 父id
     * @return 所有查询到的父id的子数据
     */
    List<TbContentCategory> findContentCategory(Long pid);
    
    /**
     * 添加一个节点, 并且修改此节点的父节点状态, 将之设置为是一个父节点
     *
     * @param contentCategory 需要添加的节点
     * @return 返回影响状态信息EgoResult
     */
    EgoResult saveContentCategory(TbContentCategory contentCategory);
    
    /**
     * 更改一个节点的状态
     *
     * @param contentCategory 需要更改的节点, 如果节点的某一个属性为null, 则不更改这个属性
     * @return 返回影响状态信息EgoResult
     */
    EgoResult updateContentCategory(TbContentCategory contentCategory);
    
    /**
     * 删除一个内容分类节点. 如果删除此节点之后, 他的父节点没有了其他子节点, 将父节点的isParent字段置为false
     *
     * @param contentCategory 需要删除的分类节点
     * @return 响应信息 EgoResult
     */
    EgoResult deleteContentCategory(TbContentCategory contentCategory);
}
