package pers.jssd.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.rpc.mapper.TbUserMapper;
import pers.jssd.ego.rpc.pojo.TbUser;
import pers.jssd.ego.rpc.pojo.TbUserExample;
import pers.jssd.ego.rpc.service.UserService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    
    private final TbUserMapper userMapper;
    
    @Autowired
    public UserServiceImpl(TbUserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public EgoResult loadUserByCond(String cond, Integer type) {
        TbUserExample userExample = new TbUserExample();
        TbUserExample.Criteria criteria = userExample.createCriteria();
        if (type.equals(1)) {
            criteria.andUsernameEqualTo(cond);
        } else if (type.equals(2)) {
            criteria.andPhoneEqualTo(cond);
        } else if (type.equals(3)) {
            criteria.andEmailEqualTo(cond);
        }
        List<TbUser> tbUsers = userMapper.selectByExample(userExample);
        EgoResult egoResult = null;
        if (tbUsers != null && tbUsers.size() > 0) {
            egoResult = new EgoResult(false);
        } else {
            egoResult = new EgoResult(true);
        }
        
        return egoResult;
    }
    
    @Override
    public EgoResult addUser(TbUser user) {
        EgoResult result = null;
        int insert = userMapper.insert(user);
        if (insert > 0) {
            result = EgoResult.ok();
        } else {
            result = new EgoResult(400, null, "注册失败. 请校验数据后请再提交数据.");
        }
        return result;
    }
    
    @Override
    public TbUser findUserByUsername(String username) {
        
        TbUserExample userExample = new TbUserExample();
        TbUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = userMapper.selectByExample(userExample);
        if (tbUsers != null && tbUsers.size() == 1) {
            return tbUsers.get(0);
        }
        return null;
    }
}
