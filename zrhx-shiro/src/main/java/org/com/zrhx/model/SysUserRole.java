package org.com.zrhx.model;

public class SysUserRole {
    private String userroleID;

    private String roleID;

    private String userID;

    public String getUserroleID() {
        return userroleID;
    }

    public void setUserroleID(String userroleID) {
        this.userroleID = userroleID == null ? null : userroleID.trim();
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID == null ? null : roleID.trim();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }
}