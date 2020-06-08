package pers.jssd.ego.rpc.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbContent;

import java.util.List;

/**
 * 内容管理服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ContentService {
    
    /**
     * 查询对应内容分类的所有内容信息
     *
     * @param categoryId 内容的分类id
     * @param page 当前页
     * @param rows 每页多少条数据
     * @return 返回分页信息
     */
    PageResult<TbContent> findContent(Long categoryId, Integer page, Integer rows);
    
    /**
     * 查询某个内容类对应的内容信息, 不进行分页显示
     *
     * @param cateGoryId 内容类别
     * @return 返回查询到的所有内容信息
     */
    List<TbContent> findContent(Long cateGoryId);
    
    /**
     * 添加一个内容信息
     *
     * @param content 需要添加的内容信息
     * @return 返回响应信息EgoResult
     */
    EgoResult addContent(TbContent content);
    
    /**
     * 更改内容信息
     *
     * @param content 需要更新的内容信息
     * @return 返回响应信息EgoResult
     */
    EgoResult updateContent(TbContent content);
    
    /**
     * 批量删除内容信息
     *
     * @param ids 需要删除的内容信息
     * @return 返回响应信息EgoResult
     */
    EgoResult deleteContent(List<Long> ids);
}
