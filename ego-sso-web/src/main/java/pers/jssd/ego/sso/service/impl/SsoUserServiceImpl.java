package pers.jssd.ego.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.rpc.pojo.TbUser;
import pers.jssd.ego.rpc.service.UserService;
import pers.jssd.ego.sso.service.SsoUserService;
import pers.jssd.ego.utils.CookieUtils;
import pers.jssd.ego.utils.JsonUtil;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class SsoUserServiceImpl implements SsoUserService {
    
    /**
     * 远程用户服务代理类
     */
    private final UserService userService;
    
    /**
     * redis操作客户端
     */
    private final JedisCluster jedisCluster;
    
    @Autowired
    public SsoUserServiceImpl(UserService userService, JedisCluster jedisCluster) {
        this.userService = userService;
        this.jedisCluster = jedisCluster;
    }
    
    @Override
    public EgoResult loadUserByCond(String cond, Integer type) {
        
        return userService.loadUserByCond(cond, type);
    }
    
    @Override
    public EgoResult addUser(String username, String password, String phone, String email) {
        TbUser user = new TbUser();
        user.setUsername(username);
        // md5加密
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(pwd);
        user.setPhone(phone);
        user.setEmail(email);
        Date date = new Date();
        user.setUpdated(date);
        user.setCreated(date);
        
        return userService.addUser(user);
    }
    
    @Override
    public EgoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult;
        
        TbUser user = userService.findUserByUsername(username);
        if (user != null) {
            // 对前端密码加密
            String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
            if (user.getPassword().equals(pwd)) {
                String token = UUID.randomUUID().toString();
                String userStr = JsonUtil.objectToJson(user);
                // 将用户信息存储得redis
                jedisCluster.set(token, userStr);
                jedisCluster.expire(token, 1800);
                
                // 将token信息响应到前端
                // 设置cookie
                CookieUtils.setCookie(request, response, "sso_token", token);
                
                egoResult = new EgoResult(token);
                return egoResult;
            }
        }
        
        egoResult = new EgoResult(400, null, "error");
        return egoResult;
    }
    
    @Override
    public EgoResult loadUserByToken(String token) {
        EgoResult egoResult;
        String s = jedisCluster.get(token);
        if (!StringUtils.isEmpty(s)) {
            TbUser user = JsonUtil.jsonToPojo(s, TbUser.class);
            if (user != null) {
                user.setPassword(null);
                egoResult = new EgoResult(user);
            } else {
                egoResult = new EgoResult(400, "error");
            }
        } else {
            egoResult = new EgoResult(400, "error");
        }
        
        return egoResult;
    }
    
    @Override
    public EgoResult logout(String token) {
        EgoResult egoResult = null;
        
        Long del = jedisCluster.del(token);
        if (!del.equals(0L)) {
            egoResult = new EgoResult("");
            return egoResult;
        }
        egoResult = new EgoResult(400, "error");
        
        return egoResult;
    }
}
