package org.com.zrhx.service;

import org.com.zrhx.model.SysUser;

import java.util.List;

/**
 * @Title: SysUserService
 * @Description:  用户服务类
 * @author: gs  
 * @date: 2017/5/26 10:50 
 */
public interface SysUserService  extends  BaseService<SysUser> {


    /**
     * 查询用户的所有权限
     * @param userID  用户ID
     */
    List<String> queryAllPerms(String userID);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userID);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByLoginName(String LoginName);
    /**
     * 修改密码
     * @param userID       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    int updatePassword(String userID, String password, String newPassword);
}
