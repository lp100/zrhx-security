package org.com.zrhx.service;


import org.com.zrhx.model.SysPermission;

import java.util.List;


/**
 * @Title: SysPermissionServiceImpl
 * @Description:  权限管理
 * @author: gs
 * @date: 2017/6/7 16:58
 */
public interface SysPermissionService extends BaseService<SysPermission>{

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysPermission> queryListParentId(String parentId, List<String> menuIdList);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysPermission> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysPermission> queryUserList(String userId);
}
