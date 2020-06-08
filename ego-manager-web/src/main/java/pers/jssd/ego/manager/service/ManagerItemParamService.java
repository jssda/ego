package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbItemParam;

/**
 * 分页显示所有商品属性模板
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerItemParamService {

    /**
     * 查询商品类对应的商品属性模板, 并分页显示
     *
     * @param page 当前页
     * @param rows 每页显示多少数据
     * @return 返回商品分页对象PageResult
     */
    PageResult<TbItemParam> findItemParam(Integer page, Integer rows);

    /**
     * 通过商品类目, 取得商品的参数模板信息
     * @param cid 商品的类目id
     * @return 返回取得的商品类目模板信息EgoResult, 如果没有商品的参数模板, 返回null
     */
    EgoResult getItemParamByCid(Long cid);

    /**
     * 存储itemParam信息
     *
     *
     * @param cid 需要存储的商品模板对应的商品类目
     * @param paramData 存储的商品模板信息
     * @return 返回是否存储成功的响应对象EgoResult, 如果没有存储成功, 返回null
     */
    EgoResult saveItemParam(Long cid, String paramData);

    /**
     * 批量删除ItemParam信息
     *
     * @param ids 需要删除的id
     * @return 返回是否删除成功的响应对象EgoResult
     */
    EgoResult deleteItemParam(Long[] ids);

}
