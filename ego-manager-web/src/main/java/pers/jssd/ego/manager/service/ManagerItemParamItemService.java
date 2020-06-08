package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.EgoResult;

/**
 * 商品规格参数服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerItemParamItemService {

    /**
     * 通过商品的id, 查询商品的规格参数信息
     * @param itemId 商品的id
     * @return 返回响应对象EgoResult
     */
    EgoResult findItemParamItemById(Long itemId);

}
