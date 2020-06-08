package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.manager.service.ManagerItemDescService;
import pers.jssd.ego.rpc.pojo.TbItemDesc;
import pers.jssd.ego.rpc.service.ItemDescService;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {

    private final ItemDescService itemDescService;

    public ManagerItemDescServiceImpl(ItemDescService itemDescService) {
        this.itemDescService = itemDescService;
    }


    @Override
    public EgoResult getItemDesc(Long id) {
        TbItemDesc itemDesc = itemDescService.getItemDesc(id);
        EgoResult egoResult = new EgoResult();
        egoResult.setData(itemDesc);
        egoResult.setStatus(200);
        egoResult.setMsg("ok");
        return egoResult;
    }
}
