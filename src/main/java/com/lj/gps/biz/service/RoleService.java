package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.Role;
import com.lj.gps.biz.entity.RoleDevice;
import com.lj.gps.biz.mapper.RoleDeviceMapper;
import com.lj.gps.biz.mapper.RoleMapper;
import com.lj.gps.biz.mapper.UserRoleMapper;
import com.lj.gps.frame.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/7<br/>
 * Time: 17:03<br/>
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleDeviceMapper roleDeviceMapper;

    /**
     * 获取角色列表
     *
     * @param role
     * @return
     */
    public List<Role> getRoleList(Role role) {
        return roleMapper.selectAll(role);
    }

    /**
     * 根据角色名获取角色
     *
     * @param roleName
     * @return
     */
    public Role getByName(String roleName) {
        return roleMapper.selectByName(roleName);
    }


    /**
     * 保存角色
     *
     * @param role
     */
    public void addRole(Role role,String sns) {
        role.setCreatetime(new Date());
        role.setUpdatetime(new Date());
        roleMapper.insert(role);
        addRoleDeviceRelations(role.getRoleid(),sns);
    }

    private void addRoleDeviceRelations(Integer roleId,String sns){
        String[] aSns=sns.split(",");
        String sn;
        RoleDevice roleDevice;
        if(StringUtils.isNotEmpty(sns)){
            for (int i = 0,j=aSns.length; i < j ; i++) {
                sn=aSns[i];
                roleDevice=new RoleDevice();
                roleDevice.setRoleid(roleId);
                roleDevice.setSn(sn);
                roleDeviceMapper.insert(roleDevice);
            }
        }
    };

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    public Role getById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新角色
     *
     * @param role
     */
    public void updateRole(Role role,String sns) {
        role.setUpdatetime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
        roleDeviceMapper.deleteRelationsByRoleId(role.getRoleid());
        addRoleDeviceRelations(role.getRoleid(),sns);
    }

    /**
     * 批量删除角色
     *
     * @param ids
     */
    public void removeRole(String ids) {
        if (StringUtils.isBlank(ids)) return;
        String[] ugIds = ids.split(",");
        for (String id : ugIds) {
            Integer roleId=Integer.parseInt(id);
            userRoleMapper.deleteRelationsByRoleId(roleId);
            roleDeviceMapper.deleteRelationsByRoleId(roleId);
            roleMapper.remove(roleId);
        }
    }


    /**
     * 获取所有角色
     *
     * @return
     */
    public List<Role> getAllRoles() {
        return roleMapper.selectAll(null);
    }

}
