package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.dao.CatItemDao;
import pers.jssd.ego.rpc.entity.CarItem;
import pers.jssd.ego.rpc.service.CarItemService;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.service.ItemService;
import pers.jssd.ego.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class CarItemServiceImpl implements CarItemService {
    
    /**
     * 远程操作redis代理对象
     */
    private final CatItemDao catItemDao;
    
    /**
     * 远程商品服务代理对象
     */
    private final ItemService itemService;
    
    @Autowired
    public CarItemServiceImpl(CatItemDao catItemDao, ItemService itemService) {
        this.catItemDao = catItemDao;
        this.itemService = itemService;
    }
    
    @Override
    public void addItemToCar(Long uid, Long itemId) {
        // 取得需要添加的商品信息
        TbItem item = itemService.findItem(itemId);
        
        // 取得此用户的购物车
        Map<String, String> carMap = catItemDao.getCarMap(String.valueOf(uid));
        // 如果此用户第一次购物的话. 建立一个购物车
        if (carMap == null) {
            carMap = new HashMap<>();
        }
        // 从购物车中获取要添加的商品, 如果没有此商品, 添加此商品, 如果有, 将此商品的购买信息加一
        String sCarItem = carMap.get(String.valueOf(itemId));
        CarItem carItem = null;
        // 购物车中没有此商品. 建立此商品
        if (sCarItem == null) {
            carItem = new CarItem();
            carItem.setItem(item);
            carItem.setNum(1);
        } else {
            // 购物车中有此商品, 取得此商品, 数量加一
            carItem = JsonUtil.jsonToPojo(sCarItem, CarItem.class);
            if (carItem != null) {
                carItem.setNum(carItem.getNum() + 1);
            }
        }
        // 将商品添加到购物车
        sCarItem = JsonUtil.objectToJson(carItem);
        carMap.put(String.valueOf(itemId), sCarItem);
        
        // 购物车存储到redis数据库
        catItemDao.setCarMap(String.valueOf(uid), carMap);
    }
    
    @Override
    public Map<Long, CarItem> loadCarList(Long uid) {
        Map<Long, CarItem> result = new HashMap<>();
        Map<String, String> carMap = catItemDao.getCarMap(String.valueOf(uid));
        for (Map.Entry<String, String> entry : carMap.entrySet()) {
            Long key = Long.valueOf(entry.getKey());
            CarItem value = JsonUtil.jsonToPojo(entry.getValue(), CarItem.class);
            
            result.put(key, value);
        }
        
        return result;
    }
    
    @Override
    public String updateCarItemNum(Long uid, Long itemId, Integer num) {
        String carItemStr = catItemDao.getCarItemInfo(String.valueOf(uid), String.valueOf(itemId));
        if (carItemStr != null) {
            CarItem carItem = JsonUtil.jsonToPojo(carItemStr, CarItem.class);
            if (carItem != null) {
                carItem.setNum(num);
                carItemStr = JsonUtil.objectToJson(carItem);
                catItemDao.setCarItem(String.valueOf(uid), String.valueOf(itemId), carItemStr);
                return "OK";
            }
        }
        return "error";
    }
    
    @Override
    public void deleteCarItem(Long uid, Long itemId) {
        catItemDao.deleteCarItem(String.valueOf(uid), String.valueOf(itemId));
    }
    
    @Override
    public void deleteCarMap(Long uid) {
        catItemDao.deleteCarMap(String.valueOf(uid));
    }
}
