package pers.jssd.ego.sso.service;

import pers.jssd.ego.beans.EgoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface SsoUserService {
    
    /**
     * 验证用户名唯一性
     *
     * @param cond 验证得内容
     * @param type 验证得类别 可选参数 1、 2、 3 分别代表 username、 phone、 email
     * @return 响应用户是否唯一性封装EgoResult
     * <pre>
     *           {
     *              status: 200 //200 成功
     *               msg: "OK"// 返回信息消息
     *               data: false // 返回数据， true： 数据可用， false： 数据不可用
     *           }
     * </pre>
     */
    EgoResult loadUserByCond(String cond, Integer type);
    
    /**
     * 添加一个用户
     *
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @param email    邮箱
     * @return 返回响应信息EgoResult
     */
    EgoResult addUser(String username, String password, String phone, String email);
    
    /**
     * 用户登录
     *
     * @param username 用户姓名
     * @param password 用户密码
     * @return 返回用户登录状态
     */
    EgoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 获得用户的登录状态
     *
     * @param token 用户的登录令牌
     * @return 返回用户的登录状态
     */
    EgoResult loadUserByToken(String token);
    
    /**
     * 用户安全登出
     *
     * @param token 用户登录令牌
     * @return 返回用户登出状态
     */
    EgoResult logout(String token);
    
}
