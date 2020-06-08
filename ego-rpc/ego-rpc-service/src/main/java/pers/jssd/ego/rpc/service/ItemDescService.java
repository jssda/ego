package pers.jssd.ego.rpc.service;

import pers.jssd.ego.rpc.pojo.TbItemDesc;

/**
 * 商品描述信息服务
 *
 * @author jssdjing@gmail.com
 */
public interface ItemDescService {

    /**
     * 通过商品id, 取得商品描述信息
     *
     * @param id 商品的id
     * @return 返回商品描述信息对象 TbItemDesc
     */
    TbItemDesc getItemDesc(Long id);

}
