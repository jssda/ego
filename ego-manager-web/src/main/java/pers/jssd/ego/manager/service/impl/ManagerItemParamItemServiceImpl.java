package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.manager.service.ManagerItemParamItemService;
import pers.jssd.ego.rpc.pojo.TbItemParamItem;
import pers.jssd.ego.rpc.service.ItemParamItemService;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerItemParamItemServiceImpl implements ManagerItemParamItemService {

    private final ItemParamItemService itemParamItemService;

    public ManagerItemParamItemServiceImpl(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ItemParamItemService itemParamItemService) {
        this.itemParamItemService = itemParamItemService;
    }

    @Override
    public EgoResult findItemParamItemById(Long itemId) {
        TbItemParamItem itemParamItem = itemParamItemService.findItemParamItemById(itemId);
        return new EgoResult(itemParamItem);
    }
}
