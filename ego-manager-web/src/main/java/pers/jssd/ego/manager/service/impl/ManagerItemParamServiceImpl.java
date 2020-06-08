package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.manager.service.ManagerItemParamService;
import pers.jssd.ego.rpc.pojo.TbItemParam;
import pers.jssd.ego.rpc.service.ItemParamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    private final ItemParamService itemParamService;

    @Autowired
    public ManagerItemParamServiceImpl(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ItemParamService itemParamService) {
        this.itemParamService = itemParamService;
    }

    @Override
    public PageResult<TbItemParam> findItemParam(Integer page, Integer rows) {
        return itemParamService.findItemParam(page, rows);
    }

    @Override
    public EgoResult getItemParamByCid(Long cid) {
        TbItemParam itemParamByCid = itemParamService.getItemParamByCid(cid);

        EgoResult result = new EgoResult();
        result.setStatus(200);
        if (itemParamByCid != null) {
            result.setData(itemParamByCid);
            result.setMsg("查询到了数据");
        }
        return result;
    }

    @Override
    public EgoResult saveItemParam(Long cid, String paramData) {

        Date date = new Date();

        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        return itemParamService.saveItemParam(itemParam);
    }

    @Override
    public EgoResult deleteItemParam(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        return itemParamService.deleteItemParam(list);
    }
}
