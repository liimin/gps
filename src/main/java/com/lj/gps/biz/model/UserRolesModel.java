package com.lj.gps.biz.model;

/**
 * 用户角色实体类
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/22<br/>
 * Time: 13:03<br/>
 */
public class UserRolesModel {//extends UserInfo {

    private String userId;
    private String userName;
    /**
     * 用户角色
     */
    private String userRoleIds;
    private String userRoleNames;

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }


    public String getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public String getUserRoleNames() {
        return userRoleNames;
    }

    public void setUserRoleNames(String userRoleNames) {
        this.userRoleNames = userRoleNames;
    }
}
