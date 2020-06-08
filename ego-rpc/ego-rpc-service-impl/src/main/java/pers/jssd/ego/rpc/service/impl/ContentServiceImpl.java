package pers.jssd.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.mapper.TbContentMapper;
import pers.jssd.ego.rpc.pojo.TbContent;
import pers.jssd.ego.rpc.pojo.TbContentExample;
import pers.jssd.ego.rpc.service.ContentService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ContentServiceImpl implements ContentService {
    
    private final TbContentMapper contentMapper;
    
    public ContentServiceImpl(TbContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }
    
    @Override
    public PageResult<TbContent> findContent(Long categoryId, Integer page, Integer rows) {
        
        PageHelper.startPage(page, rows);
        TbContentExample contentExample = new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(contentExample);
        PageResult<TbContent> pageResult = new PageResult<>();
        pageResult.setRows(tbContents);
        pageResult.setTotal(new PageInfo<>(tbContents).getTotal());
        
        return pageResult;
    }
    
    @Override
    public List<TbContent> findContent(Long categoryId) {
        TbContentExample contentExample = new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        
        return contentMapper.selectByExampleWithBLOBs(contentExample);
    }
    
    @Override
    public EgoResult addContent(TbContent content) {
        
        contentMapper.insert(content);
        return EgoResult.ok();
    }
    
    @Override
    public EgoResult updateContent(TbContent content) {
        
        contentMapper.updateByPrimaryKeySelective(content);
        return EgoResult.ok();
    }
    
    @Override
    public EgoResult deleteContent(List<Long> ids) {
        TbContentExample contentExample = new TbContentExample();
        TbContentExample.Criteria contentExampleCriteria = contentExample.createCriteria();
        contentExampleCriteria.andIdIn(ids);
        
        contentMapper.deleteByExample(contentExample);
        
        return EgoResult.ok();
    }
}
