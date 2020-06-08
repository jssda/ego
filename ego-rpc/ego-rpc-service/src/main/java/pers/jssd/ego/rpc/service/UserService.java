package pers.jssd.ego.rpc.service;

import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.rpc.pojo.TbUser;

/**
 * 远程用户服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface UserService {
    
    /**
     * 验证用户唯一性
     *
     * @param cond 验证的数据
     * @param type 验证类型 可选参数 1、 2、 3 分别代表 username、 phone、 email
     * @return 响应用户是否唯一性封装EgoResult
     * <pre>
     *      {
     *          status: 200 //200 成功
     *          msg: "OK"// 返回信息消息
     *          data: false // 返回数据， true： 数据可用， false： 数据不可用
     *      }
     * </pre>
     */
    EgoResult loadUserByCond(String cond, Integer type);
    
    
    /**
     * 添加一个用户
     *
     * @param user 需要添加得用户
     * @return 返回响应信息EgoResult
     */
    EgoResult addUser(TbUser user);
    
    
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 返回查询到得用户
     */
    TbUser findUserByUsername(String username);
}
