package org.com.zrhx.model;


import org.com.zrhx.enity.BaseEntity;

public class SysRole extends BaseEntity<SysRole>{
    private String roleID;

    private String roleName;

    private String memo;


    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID == null ? null : roleID.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }


}