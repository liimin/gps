package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.entity.UserRole;
import com.lj.gps.biz.mapper.UserMapper;
import com.lj.gps.biz.mapper.UserRoleMapper;
import com.lj.gps.biz.model.UserRolesModel;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.utils.StringUtils;
import com.lj.gps.frame.utils.encryption.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/7<br/>
 * Time: 17:45<br/>
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户ID获取用户信息
     *
     * @param id
     * @return
     */
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param u
     * @return
     */
    public List<User> getUserList(User u) {
        List<User> uList=userMapper.selectAllUser(u);
        UserRolesModel roleModel = null;
        for (User user : uList) {
            roleModel = userMapper.getUserRoleModelByUserId(user.getUserid());
           // user.setPassword(null);
            if (roleModel != null) {
                user.setRoleNames(roleModel.getUserRoleNames());
                user.setRoleIds(roleModel.getUserRoleIds());
            }
        }
        return uList;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    public void addUser(User user) {
        user.setStatus(Constants.USER_AVAILABLE_STATUS);
        user.setCreatetime(new Date());
        user.setPassword(EncryptUtil.PBEEncrypt(user.getPassword()));
        userMapper.insert(user);
        updateUserRole(user);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    public void updateUser(User user) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(EncryptUtil.PBEEncrypt(user.getPassword()));
        }
        userMapper.updateByPrimaryKeySelective(user);
        updateUserRole(user);
    }

    private void updateUserRole(User user){
        if (StringUtils.isNotEmpty(user.getRoleIds())) {
            userRoleMapper.deleteRelationsByUserId(user.getUserid());
            String[] aRoleIds = user.getRoleIds().split(",");
            for (String roleId : aRoleIds) {
                UserRole ur = new UserRole();
                ur.setRoleid(Integer.parseInt(roleId));
                ur.setUserid(user.getUserid());
                userRoleMapper.insert(ur);
            }
        }
    }

    /**
     * 删除用户(支持批量删除)
     *
     * @param ids
     */
    public void removeUserById(String ids) {
        if (StringUtils.isBlank(ids)) return;
        String[] ugIds = ids.split(",");
        for (String id : ugIds) {
            userRoleMapper.deleteRelationsByUserId(Integer.parseInt(id));
            userMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }
}
