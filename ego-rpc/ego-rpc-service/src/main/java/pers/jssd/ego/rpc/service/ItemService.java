package pers.jssd.ego.rpc.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.pojo.TbItemDesc;
import pers.jssd.ego.rpc.pojo.TbItemParamItem;

import java.util.List;

/**
 * 商品类的服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ItemService {
    
    /**
     * 所有商品的分页查询
     *
     * @param page 当前页
     * @param rows 每页多少条
     * @return 返回封装好的分页集合
     */
    PageResult<TbItem> findItem(Integer page, Integer rows);
    
    /**
     * 通过id, 查询商品
     *
     * @param id 商品的id
     * @return 返回查询到的商品
     */
    TbItem findItem(Long id);
    
    /**
     * 上架, 下架商品
     *
     * @param ids  需要上架的商品信息的id
     * @param flag true:上架商品. false:下架商品
     * @return 返回上架商品之后的响应信息
     */
    EgoResult updateItemStatus(List<Long> ids, boolean flag);
    
    /**
     * 删除商品
     *
     * @param ids 需要删除商品的id
     * @return 返回是否删除商品的响应信息
     */
    EgoResult removeItem(List<Long> ids);
    
    /**
     * 插入商品信息
     *
     * @param item          商品信息
     * @param desc          商品的描述信息对象
     * @param itemParamItem 商品的规格参数信息
     * @return 返回响应对象EgoResult
     */
    EgoResult insertItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem);
    
    /**
     * 更新商品数据
     *
     * @param item          新的商品信息
     * @param itemDesc      商品描述信息, 如果没有则添加, 如果有更新之
     * @param itemParamItem 商品规格参数信息
     * @return 返回是否更新成功的响应对象EgoResult
     */
    EgoResult updateItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem);
}
