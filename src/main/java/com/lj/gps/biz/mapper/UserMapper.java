package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.model.UserRolesModel;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser(User user);

    /**
     * 获取用户角色及相关角色信息
     *
     * @param userId
     * @return
     */
    UserRolesModel getUserRoleModelByUserId(Integer userId);

    /**
     * 根据username获取用户
     *
     * @param username
     * @return
     */
    @Select("select * from B_USER where USERNAME = #{username}")
    @ResultMap(value = "BaseResultMap")
    User getUserByUsername(String username);
}