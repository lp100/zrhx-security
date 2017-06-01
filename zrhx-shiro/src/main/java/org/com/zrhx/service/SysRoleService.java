package org.com.zrhx.service;
import org.com.zrhx.model.SysRole;

import java.util.List;



/**
 * @Title: SysRoleService
 * @Description: 角色
 * @author: gs
 * @date: 2017/5/26 11:07
 */
public interface SysRoleService extends BaseService<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<String> queryRoleIdList(String createUserId);
}
