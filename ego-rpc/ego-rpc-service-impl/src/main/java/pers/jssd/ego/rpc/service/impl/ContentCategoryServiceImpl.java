package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.rpc.mapper.TbContentCategoryMapper;
import pers.jssd.ego.rpc.pojo.TbContentCategory;
import pers.jssd.ego.rpc.pojo.TbContentCategoryExample;
import pers.jssd.ego.rpc.service.ContentCategoryService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    
    private final TbContentCategoryMapper contentCategoryMapper;
    
    public ContentCategoryServiceImpl(TbContentCategoryMapper contentCategoryMapper) {
        this.contentCategoryMapper = contentCategoryMapper;
    }
    
    @Override
    public List<TbContentCategory> findContentCategory(Long pid) {
        
        TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria contentCategoryExampleCriteria = contentCategoryExample.createCriteria();
        contentCategoryExampleCriteria.andParentIdEqualTo(pid);
        
        return contentCategoryMapper.selectByExample(contentCategoryExample);
    }
    
    @Override
    public EgoResult saveContentCategory(TbContentCategory contentCategory) {
        EgoResult result = null;
        
        // 添加子节点
        contentCategoryMapper.insert(contentCategory);
        // 将此节点的父节点状态设置为是一个父节点
        TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria contentCategoryExampleCriteria = contentCategoryExample.createCriteria();
        contentCategoryExampleCriteria.andIdEqualTo(contentCategory.getParentId());
        TbContentCategory record = new TbContentCategory();
        record.setIsParent(true);
        contentCategoryMapper.updateByExampleSelective(record, contentCategoryExample);
        // 返回是否修改成功的状态
        result = EgoResult.ok();
        
        return result;
    }
    
    @Override
    public EgoResult updateContentCategory(TbContentCategory contentCategory) {
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        
        return EgoResult.ok();
    }
    
    @Override
    public EgoResult deleteContentCategory(TbContentCategory contentCategory) {
        
        // 取得此节点的父节点
        TbContentCategory contentCategory1 = contentCategoryMapper.selectByPrimaryKey(contentCategory.getId());
        Long parentId = contentCategory1.getParentId();
    
        // 删除当前节点
        contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
    
        // 设置查询条件
        TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria contentCategoryExampleCriteria = contentCategoryExample.createCriteria();
        contentCategoryExampleCriteria.andParentIdEqualTo(parentId);
        // 查询父节点id=此节点父节点id是否还有
        int i = contentCategoryMapper.countByExample(contentCategoryExample);
        if (i == 0) {
            TbContentCategory record = new TbContentCategory();
            record.setId(parentId);
            record.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(record);
        }
    
        return EgoResult.ok();
    }
}
