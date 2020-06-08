package pers.jssd.ego.rpc.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbItemParam;

import java.util.List;

/**
 * 商品属性模板业务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ItemParamService {

    /**
     * 查询所有商品属性模板信息, 并分页显示
     *
     * @param page 当前页
     * @param rows 每页多少行数据
     * @return 返回封装分页结果的对象
     */
    PageResult<TbItemParam> findItemParam(Integer page, Integer rows);

    /**
     * 通过商品类目id, 取得此类目的商品属性模板
     *
     * @param cid 商品的类别id
     * @return 返回查询到的此商品类匹配的商品属性模板, 如果没有, 返回null
     */
    TbItemParam getItemParamByCid(Long cid);

    /**
     * 存储itemParam
     *
     * @return 返回是否存储成功的响应对象
     */
    EgoResult saveItemParam(TbItemParam itemParam);

    /**
     * 批量删除itemParam信息
     * @param ids 需要删除的ItemParam的id集合
     * @return 返回是否删除成功的响应对象EgoResult
     */
    EgoResult deleteItemParam(List<Long> ids);

}
