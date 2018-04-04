package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record); /**
     * 通过登录用户ID 查询用户对应权限
     *
     * @return
     */
    List<UserRole> selectListByUserId(@Param("userId") Integer userId);

    /**
     * 根据账号id删除 账号角色关系
     *
     * @param userId
     */
    @Delete("DELETE FROM B_USER_ROLE WHERE USERID = #{userId}")
    void deleteRelationsByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色id删除 账号角色关系
     *
     * @param roleId
     */
    @Delete("DELETE FROM B_USER_ROLE WHERE ROLEID = #{roleId}")
    void deleteRelationsByRoleId(@Param("roleId") Integer roleId);


}