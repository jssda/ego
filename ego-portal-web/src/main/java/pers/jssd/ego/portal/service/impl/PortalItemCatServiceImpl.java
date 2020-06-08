package pers.jssd.ego.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ego.beans.CatNode;
import pers.jssd.ego.beans.CatResult;
import pers.jssd.ego.portal.service.PortalItemCatService;
import pers.jssd.ego.rpc.pojo.TbItemCat;
import pers.jssd.ego.rpc.service.ItemCatService;
import pers.jssd.ego.utils.JsonUtil;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {
    
    @Value("${ITEM_CAT}")
    private String itemCatKey;
    
    /**
     * redis集群客户端
     */
    private final JedisCluster jedisCluster;
    
    /**
     * 商品类目服务
     */
    private final ItemCatService itemCatService;
    
    @Autowired
    public PortalItemCatServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ItemCatService itemCatService, JedisCluster jedisCluster) {
        this.itemCatService = itemCatService;
        this.jedisCluster = jedisCluster;
    }
    
    @Override
    public String loadItemCat() {
        // 查询redis内存数据库
        String str = jedisCluster.get(itemCatKey);
        if (!StringUtils.isEmpty(str)) {
            return str;
        }
        
        
        List<TbItemCat> itemCat = itemCatService.findItemCat();
        CatResult catResult = new CatResult();
        List<?> list = getChildren(0L, itemCat);
        catResult.setData(list);
        String result = JsonUtil.objectToJson(catResult);
    
        jedisCluster.set(itemCatKey, result);
        return result;
    }
    
    /**
     * 将所有分类信息, 根据父子级别, 递归处理到一个List中
     *
     * @param parentId 当前父节点id
     * @param itemCats 当前分类信息
     * @return 处理完的父子级节点信息
     */
    private List<?> getChildren(Long parentId, List<TbItemCat> itemCats) {
        // 盛放指定分类下的所有子分类信息
        List<Object> resultList = new ArrayList<>();
        
        for (TbItemCat itemCat : itemCats) {
            if (itemCat.getParentId().equals(parentId)) {
                if (itemCat.getIsParent()) {
                    // 如果itemCat代表一级分类或者二级分类
                    CatNode catNode = new CatNode();
                    if (itemCat.getParentId() == 0) {
                        // 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
                        catNode.setName(
                                "<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    } else {
                        // 如果是二级分类 "电子书刊",
                        catNode.setName(itemCat.getName());
                    }
                    // "/products/2.html",
                    catNode.setUrl("/products/" + itemCat.getId() + ".html");
                    catNode.setList(getChildren(itemCat.getId(), itemCats));
                    // 将节点添加到list集合中
                    resultList.add(catNode);
                } else {
                    // 如果itemCat表示三级分类 "/products/3.html|电子书",
                    resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
                }
            }
        }
        return resultList;
    }
}
