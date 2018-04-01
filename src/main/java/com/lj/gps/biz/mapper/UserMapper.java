package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.model.UserRolesModel;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.security.acl.LastOwnerException;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll(User user);

    /**
     * 获取用户角色及相关角色信息
     *
     * @param userId
     * @return
     */
    UserRolesModel selectUserRoleRelationsByUserId(Long userId);

    /**
     * 根据username获取用户
     *
     * @param username
     * @return
     */
    @Select("select * from B_USER where USERNAME = #{username}")
    @ResultMap(value = "BaseResultMap")
    User selectByUsername(String username);
}