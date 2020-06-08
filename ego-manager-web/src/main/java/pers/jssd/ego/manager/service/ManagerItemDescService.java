package pers.jssd.ego.manager.service;

import pers.jssd.ego.beans.EgoResult;

/**
 * 商品描述信息
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerItemDescService {

    /**
     * 通过id获取商品描述信息
     * @param id 商品的id
     * @return 返回商品的描述信息
     */
    EgoResult getItemDesc(Long id);

}
