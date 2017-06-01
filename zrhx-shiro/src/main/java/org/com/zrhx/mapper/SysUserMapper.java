package org.com.zrhx.mapper;

import org.com.zrhx.annotation.MyBatisDao;
import org.com.zrhx.model.SysUser;

import java.util.List;
@MyBatisDao
public interface SysUserMapper extends BaseDao<SysUser> {


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
    SysUser findByLoginName(String loginName);
    /**
     * 修改密码
     */
    int updatePassword(SysUser sysUser);
}