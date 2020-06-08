package pers.jssd.ego.sso.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.sso.service.SsoUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录处理用户得请求
 *
 * @author jssdjing@gmail.com
 */
@Controller
public class SsoUserController {
    
    private final SsoUserService ssoUserService;
    
    @Autowired
    public SsoUserController(SsoUserService ssoUserService) {
        this.ssoUserService = ssoUserService;
    }
    
    /**
     * 处理验证用户名唯一性得请求, 支持jsonp跨域访问
     *
     * @param param    验证内容
     * @param type     验证类别  1、 2、 3 分别代表 username、 phone、 email
     * @param callBack 是否使用JsonP方式响应
     * @return 响应对象EgoResult
     */
    @RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public MappingJacksonValue userCheck(@PathVariable String param, @PathVariable Integer type,
                                         @RequestParam(required = false) String callBack) {
        EgoResult result = ssoUserService.loadUserByCond(param, type);
        MappingJacksonValue value = new MappingJacksonValue(result);
        if (!StringUtils.isEmpty(callBack)) {
            value.setJsonpFunction(callBack);
        }
        return value;
    }
    
    /**
     * 处理添加用户得请求
     *
     * @param username 用户姓名
     * @param password 用户密码
     * @param phone    用户手机号
     * @param email    用户邮箱
     * @return 返回响应对象EgoResult
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public EgoResult addUser(String username, String password, String phone, String email) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("phone = " + phone);
        System.out.println("email = " + email);
        return ssoUserService.addUser(username, password, phone, email);
    }
    
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 如果登录成功, 响应携带者token的响应信息, 如果登录失败, 响应信息的data中为null
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public EgoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return ssoUserService.login(username, password, request, response);
    }
    
    /**
     * 检查用户是否登录成功
     *
     * @param token    用户的登录令牌
     * @param callback 是否使用jsonp
     * @return 反掌登录响应状态, 如果登录成功, EgoResult.data为用户信息json对象
     */
    @RequestMapping(value = "/user/token/{token}")
    @ResponseBody
    public MappingJacksonValue userToken(@PathVariable String token, @RequestParam(required = false) String callback) {
        EgoResult result = ssoUserService.loadUserByToken(token);
        MappingJacksonValue value = new MappingJacksonValue(result);
        if (!StringUtils.isEmpty(callback)) {
            value.setJsonpFunction(callback);
        }
        return value;
    }
    
    
    /**
     * 用户安全退出
     *
     * @param token    用户登录令牌
     * @param callback jsonp回调字符串
     * @return 返回是否安全退出的响应信息
     */
    @RequestMapping(value = "/user/logout/{token}", method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public MappingJacksonValue userLogout(@PathVariable String token, @RequestParam(required = false) String callback) {
        EgoResult logout = ssoUserService.logout(token);
        MappingJacksonValue value = new MappingJacksonValue(logout);
        if (!StringUtils.isEmpty(callback)) {
            value.setJsonpFunction(callback);
        }
        return value;
    }
    
}
