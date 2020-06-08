package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbContent;

/**
 * 后台内容管理服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerContentService {
    
    /**
     * 查询所有在指定内容类别中的内容, 并分页显示
     *
     * @param categoryId 指定的内容类别
     * @param page       当前页
     * @param rows       每页显示多少条数据
     * @return 返回分页信息
     */
    PageResult<TbContent> findContent(Long categoryId, Integer page, Integer rows);
    
    /**
     * 添加一个内容元素
     *
     * @param content 需要添加的内容元素
     * @return 返回响应信息EgoResult
     */
    EgoResult addContent(TbContent content);
    
    /**
     * 更新内容信息
     *
     * @param content 需要更新的内容信息
     * @return 返回响应信息EgoResult
     */
    EgoResult updateContent(TbContent content);
    
    /**
     * 批量删除信息
     *
     * @param ids 需要删除的内容id
     * @return 返回响应信息EgoResult
     */
    EgoResult deleteContent(Long[] ids);
    
}
