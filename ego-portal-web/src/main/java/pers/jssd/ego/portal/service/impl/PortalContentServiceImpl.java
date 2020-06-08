package pers.jssd.ego.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ego.beans.BigPicture;
import pers.jssd.ego.portal.service.PortalContentService;
import pers.jssd.ego.rpc.pojo.TbContent;
import pers.jssd.ego.rpc.service.ContentService;
import pers.jssd.ego.utils.JsonUtil;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class PortalContentServiceImpl implements PortalContentService {
    
    /**
     * redis操作客户端
     */
    private final JedisCluster jedisCluster;
    
    /**
     * 远程内容服务操作代理对象
     */
    private final ContentService contentService;
    
    @Value("${CONTENT_PICTURE}")
    private String contentPictureKey;
    
    @Autowired
    public PortalContentServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ContentService contentService, JedisCluster jedisCluster) {
        this.contentService = contentService;
        this.jedisCluster = jedisCluster;
    }
    
    @Override
    public String loadContentByCid(Long categoryId) {
        String str = jedisCluster.get(contentPictureKey);
        if (!StringUtils.isEmpty(str)) {
            return str;
        }
    
        List<TbContent> content = contentService.findContent(categoryId);
        
        List<BigPicture> result = new ArrayList<>();
        for (TbContent tbContent : content) {
            BigPicture picture = new BigPicture();
            picture.setSrc(tbContent.getPic());
            picture.setSrcB(tbContent.getPic2());
            picture.setAlt(tbContent.getTitle());
            picture.setHeight(240);
            picture.setWidth(670);
            picture.setWidthB(550);
            picture.setHref(tbContent.getUrl());
            picture.setHeightB(240);
    
            result.add(picture);
        }
    
        String json = JsonUtil.objectToJson(result);
        jedisCluster.set(contentPictureKey, json);
        
        return json;
    }
}
