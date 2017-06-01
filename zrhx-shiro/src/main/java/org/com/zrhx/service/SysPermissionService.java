package org.com.zrhx.service;


import org.com.zrhx.model.SysPermission;

import java.util.List;


/**
 * 菜单管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:16
 */
public interface SysPermissionService extends BaseService<SysPermission>{

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysPermission> queryListParentId(Long parentId, List<Long> menuIdList);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysPermission> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysPermission> queryUserList(Long userId);
}
