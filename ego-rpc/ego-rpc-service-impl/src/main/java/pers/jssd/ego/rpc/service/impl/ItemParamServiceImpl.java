package pers.jssd.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.mapper.TbItemParamMapper;
import pers.jssd.ego.rpc.pojo.TbItemParam;
import pers.jssd.ego.rpc.pojo.TbItemParamExample;
import pers.jssd.ego.rpc.service.ItemParamService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    private final TbItemParamMapper itemParamMapper;

    @Autowired
    public ItemParamServiceImpl(TbItemParamMapper itemParamMapper) {
        this.itemParamMapper = itemParamMapper;
    }

    @Override
    public PageResult<TbItemParam> findItemParam(Integer page, Integer rows) {
        PageResult<TbItemParam> result = new PageResult<>();

        PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

        result.setRows(list);
        result.setTotal((new PageInfo<>(list)).getTotal());

        return result;
    }


    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam itemParam = null;
        TbItemParamExample itemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = itemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(itemParamExample);
        if (list != null && list.size() == 1) {
            itemParam = list.get(0);
        }

        return itemParam;
    }

    @Override
    public EgoResult saveItemParam(TbItemParam itemParam) {
        EgoResult egoResult = null;
        try {
            itemParamMapper.insert(itemParam);
            egoResult = EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return egoResult;
    }

    @Override
    public EgoResult deleteItemParam(List<Long> ids) {
        EgoResult result = null;
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            itemParamMapper.deleteByExample(example);
            result = EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
