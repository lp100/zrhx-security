package org.com.zrhx.mapper;

import org.com.zrhx.annotation.MyBatisDao;
import org.com.zrhx.model.SysPermission;

import java.util.List;

@MyBatisDao
public interface SysPermissionMapper extends  BaseDao<SysPermission> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysPermission> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysPermission> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysPermission> queryUserList(String userId);
}