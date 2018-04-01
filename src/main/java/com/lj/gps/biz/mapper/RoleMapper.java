package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.Role;
import com.lj.gps.biz.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll(Role role);

    @Select("select * from B_ROLE o where o.ROLENAME = #{roleName} ")
    @Results(@Result(column = "ROLEID", property = "roleid"))
    Role selectByName(@Param(value = "roleName") String roleName);

    /**
     * 逻辑删除角色
     *
     * @param id
     */
    @Update("DELETE FROM  B_ROLE  where ROLEID = #{id}")
    void remove(@Param(value = "id") Long id);
}