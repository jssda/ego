package pers.jssd.ego.rpc.service.impl;

import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.mapper.TbItemParamItemMapper;
import pers.jssd.ego.rpc.pojo.TbItemParamItem;
import pers.jssd.ego.rpc.pojo.TbItemParamItemExample;
import pers.jssd.ego.rpc.service.ItemParamItemService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    private final TbItemParamItemMapper itemParamItemMapper;

    public ItemParamItemServiceImpl(TbItemParamItemMapper itemParamItemMapper) {
        this.itemParamItemMapper = itemParamItemMapper;
    }

    @Override
    public TbItemParamItem findItemParamItemById(Long itemId) {
        TbItemParamItem result = null;
        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = itemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> tbItemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(itemParamItemExample);
        if (tbItemParamItems != null && tbItemParamItems.size() == 1) {
            result = tbItemParamItems.get(0);
        }
        return result;
    }
}
