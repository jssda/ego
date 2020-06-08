package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.mapper.TbItemCatMapper;
import pers.jssd.ego.rpc.pojo.TbItemCat;
import pers.jssd.ego.rpc.pojo.TbItemCatExample;
import pers.jssd.ego.rpc.service.ItemCatService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    
    /**
     * 操作ItemCat表的持久层对象
     */
    private final TbItemCatMapper itemCatMapper;
    
    /**
     * 构造方式注入TbItemCatMapper
     */
    @Autowired
    public ItemCatServiceImpl(TbItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }
    
    @Override
    public List<TbItemCat> getItemCatByParentId(Long pid) {
        TbItemCatExample itemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = itemCatExample.createCriteria();
        criteria.andParentIdEqualTo(pid);
        return itemCatMapper.selectByExample(itemCatExample);
    }
    
    @Override
    public List<TbItemCat> findItemCat() {
        TbItemCatExample itemCatExample = new TbItemCatExample();
        return itemCatMapper.selectByExample(itemCatExample);
    }
}
