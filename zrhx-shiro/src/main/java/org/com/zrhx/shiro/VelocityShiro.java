package org.com.zrhx.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Title: VelocityShiro
 * @Description: Shiro权限标签(Velocity版)
 * @author: gs
 * @date: 2017/6/7 18:05
 */
public class VelocityShiro {

	/**
	 * 是否拥有该权限
	 * @param permission  权限标识
	 * @return   true：是     false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

}
