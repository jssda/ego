package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.manager.service.ManagerContentService;
import pers.jssd.ego.rpc.pojo.TbContent;
import pers.jssd.ego.rpc.service.ContentService;

import java.util.Arrays;
import java.util.Date;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerContentServiceImpl implements ManagerContentService {
    
    private final ContentService contentService;
    
    @Autowired
    public ManagerContentServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ContentService contentService) {
        this.contentService = contentService;
    }
    
    @Override
    public PageResult<TbContent> findContent(Long categoryId, Integer page, Integer rows) {
        
        return contentService.findContent(categoryId, page, rows);
    }
    
    @Override
    public EgoResult addContent(TbContent content) {
    
        Date date = new Date();
        content.setUpdated(date);
        content.setCreated(date);
        
        return contentService.addContent(content);
    }
    
    @Override
    public EgoResult updateContent(TbContent content) {
        Date date = new Date();
        content.setUpdated(date);
    
        return contentService.updateContent(content);
    }
    
    @Override
    public EgoResult deleteContent(Long[] ids) {
        return contentService.deleteContent(Arrays.asList(ids));
    }
}
