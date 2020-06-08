package pers.jssd.ego.item.interceptor;

import org.apache.commons.codec.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.jssd.ego.rpc.pojo.TbUser;
import pers.jssd.ego.utils.CookieUtils;
import pers.jssd.ego.utils.JsonUtil;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器. 购物车中, 所有的资源都需要登录. 将未登录的资源拦截, 通知其访问sso进行登录
 *
 * @author jssdjing@gmail.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private JedisCluster cluster;
    
    /**
     * 拦截器, 如果用户没有登录, 通知用户登录. 不可进行访问资源
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String sso_token = CookieUtils.getCookieValue(request, "sso_token");
        // 如果取得了token, 证明客户端有cookie存在
        if (!StringUtils.isEmpty(sso_token)) {
            String userJson = cluster.get(sso_token);
            // 如果能够取得用户信息, 那么, 此用户已经登录了
            if (!StringUtils.isEmpty(userJson)) {
                TbUser user = JsonUtil.jsonToPojo(userJson, TbUser.class);
                request.setAttribute("user", user);
                // 登录成功, 应该放行
                return true;
            }
        }
        
        // 如果没有登录. 需要访问sso进行登录. 同时, 在sso登录成功之后, 需要重定向到此次想要访问的页面
        String url = request.getRequestURL().toString();
        //重定向到登录页面. 并且将登录成功之后的跳转页面作为参数传递给登录请求处理器
        response.sendRedirect("http://localhost:8084/login?redirect="+url);
        
        return false;
    }
}
