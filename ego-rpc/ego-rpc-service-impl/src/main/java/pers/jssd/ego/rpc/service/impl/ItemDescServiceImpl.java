package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.mapper.TbItemDescMapper;
import pers.jssd.ego.rpc.pojo.TbItemDesc;
import pers.jssd.ego.rpc.service.ItemDescService;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    private final TbItemDescMapper itemDescMapper;

    @Autowired
    public ItemDescServiceImpl(TbItemDescMapper itemDescMapper) {
        this.itemDescMapper = itemDescMapper;
    }

    @Override
    public TbItemDesc getItemDesc(Long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }
}
