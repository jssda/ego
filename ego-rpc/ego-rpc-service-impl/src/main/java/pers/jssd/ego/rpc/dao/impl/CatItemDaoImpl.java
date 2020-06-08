package pers.jssd.ego.rpc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.jssd.ego.rpc.dao.CatItemDao;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
@Repository
public class CatItemDaoImpl implements CatItemDao {
    
    private final JedisCluster cluster;
    
    @Autowired
    public CatItemDaoImpl(JedisCluster cluster) {
        this.cluster = cluster;
    }
    
    @Override
    public void setCarMap(String uid, Map<String, String> map) {
        cluster.hmset(uid, map);
    }
    
    @Override
    public void setCarItem(String uid, String itemId, String carItemStr) {
        cluster.hset(uid, itemId, carItemStr);
    }
    
    @Override
    public Map<String, String> getCarMap(String uid) {
        return cluster.hgetAll(uid);
    }
    
    @Override
    public String getCarItemInfo(String uid, String itemId) {
        return cluster.hget(uid, itemId);
    }
    
    @Override
    public void deleteCarItem(String uid, String itemId) {
        
        cluster.hdel(uid, itemId);
    }
    
    @Override
    public void deleteCarMap(String uid) {
        cluster.del(uid);
    }
}
