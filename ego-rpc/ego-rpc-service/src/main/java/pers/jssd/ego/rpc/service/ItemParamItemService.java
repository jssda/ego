package pers.jssd.ego.rpc.service;

import pers.jssd.ego.rpc.pojo.TbItemParamItem;

/**
 * 商品参数服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ItemParamItemService {

    /**
     * 通过商品id查询商品的规格参数信息
     *
     * @param itemId 商品id
     * @return 返回查询到的商品规格参数信息
     */
    TbItemParamItem findItemParamItemById(Long itemId);

}
