package org.com.zrhx.model;

public class SysRolePermission {
    private String relationID;

    private String roleID;

    private String permissionID;

    public String getRelationID() {
        return relationID;
    }

    public void setRelationID(String relationID) {
        this.relationID = relationID == null ? null : relationID.trim();
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID == null ? null : roleID.trim();
    }

    public String getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(String permissionID) {
        this.permissionID = permissionID == null ? null : permissionID.trim();
    }
}