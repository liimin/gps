package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.RoleDevice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDevice record);

    int insertSelective(RoleDevice record);

    RoleDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleDevice record);

    int updateByPrimaryKey(RoleDevice record);

    /**
     * 根据角色id删除 账号角色关系
     *
     * @param roleId
     */
    @Delete("DELETE FROM B_ROLE_DEVICE WHERE ROLEID = #{roleId}")
    void deleteRelationsByRoleId(@Param("roleId") Integer roleId);


    List<RoleDevice> selectAll(String roleIds);

    List<String> selectAllSns(String roleIds);
}