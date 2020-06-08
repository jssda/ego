package pers.jssd.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.mapper.TbItemDescMapper;
import pers.jssd.ego.rpc.mapper.TbItemMapper;
import pers.jssd.ego.rpc.mapper.TbItemParamItemMapper;
import pers.jssd.ego.rpc.pojo.*;
import pers.jssd.ego.rpc.service.ItemService;

import java.util.List;

/**
 * tbItem商品服务类
 *
 * @author jssdjing@gmail.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    /**
     * 商品对应数据库操作的持久层类
     */
    private final TbItemMapper itemMapper;

    /**
     * 商品信息描述对应的持久层对象
     */
    private final TbItemDescMapper itemDescMapper;

    /**
     * 商品规格参数信息对应持久层对象
     */
    private final TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    public ItemServiceImpl(TbItemMapper tbItemMapper, TbItemDescMapper itemDescMapper,
                           TbItemParamItemMapper itemParamItemMapper) {
        this.itemMapper = tbItemMapper;
        this.itemDescMapper = itemDescMapper;
        this.itemParamItemMapper = itemParamItemMapper;
    }

    @Override
    public PageResult<TbItem> findItem(Integer page, Integer rows) {

        // 执行分页操作
        PageHelper.startPage(page, rows);

        // 设置查询条件
        TbItemExample itemExample = new TbItemExample();

        // 执行查询
        List<TbItem> tbItems = itemMapper.selectByExample(itemExample);

        // 封装分页结果并返回
        return new PageResult<>(tbItems, new PageInfo<>(tbItems).getTotal());
    }
    
    @Override
    public TbItem findItem(Long id) {
    
        return itemMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public EgoResult updateItemStatus(List<Long> ids, boolean flag) {

        TbItem tbItem = new TbItem();
        if (flag) {
            tbItem.setStatus((byte) 1);
        } else {
            tbItem.setStatus((byte) 2);
        }

        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        itemMapper.updateByExampleSelective(tbItem, example);

        return EgoResult.ok();
    }

    @Override
    public EgoResult removeItem(List<Long> ids) {

        // 删除商品信息
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        itemMapper.deleteByExample(example);
        
        // 删除商品描述信息
        TbItemDescExample itemDescExample = new TbItemDescExample();
        TbItemDescExample.Criteria criteria1 = itemDescExample.createCriteria();
        criteria1.andItemIdIn(ids);
        itemDescMapper.deleteByExample(itemDescExample);

        // 删除商品参数信息
        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria2 = itemParamItemExample.createCriteria();
        criteria2.andItemIdIn(ids);
        itemParamItemMapper.deleteByExample(itemParamItemExample);
    
        return EgoResult.ok();
    }

    @Override
    public EgoResult insertItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem) {

        itemMapper.insert(item);
        itemDescMapper.insert(desc);
        itemParamItemMapper.insert(itemParamItem);

        return EgoResult.ok();
    }

    @Override
    public EgoResult updateItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) {

        TbItemDescExample itemDescExample = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = itemDescExample.createCriteria();
        criteria.andItemIdEqualTo(item.getId());

        // 查询此商品是否有描述信息, 如果没有, 添加描述信息, 如果有, 更新之
        int i = itemDescMapper.countByExample(itemDescExample);
        // 有商品信息
        if (i > 0) {
            itemDesc.setCreated(null);
            itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        } else {
            itemDescMapper.insert(itemDesc);
        }
        
        // 查询此商品是否有商品规格参数信息, 如果没有, 添加, 如果有,更新之
        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria1 = itemParamItemExample.createCriteria();
        criteria1.andItemIdEqualTo(item.getId());
        int itemParamItemCount = itemParamItemMapper.countByExample(itemParamItemExample);
        if (itemParamItemCount > 0) {
            itemParamItem.setCreated(null);
            itemParamItemMapper.updateByExampleSelective(itemParamItem, itemParamItemExample);
        } else {
            itemParamItemMapper.insert(itemParamItem);
        }
    
        itemMapper.updateByPrimaryKeySelective(item);

        return EgoResult.ok();
    }
}
