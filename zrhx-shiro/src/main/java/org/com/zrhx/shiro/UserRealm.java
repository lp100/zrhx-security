package org.com.zrhx.shiro;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.com.zrhx.model.SysPermission;
import org.com.zrhx.model.SysUser;
import org.com.zrhx.service.SysPermissionService;
import org.com.zrhx.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Title: UserRealm
 * @Description: 认证
 * @author: gs
 * @date: 2017/5/26 11:26
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user = (SysUser)principals.getPrimaryPrincipal();
		String userId = user.getUserID();
		
		List<String> permsList = null;
		
		//系统管理员，拥有最高权限
		if("1".equals(userId)){
			List<SysPermission> permissionList = sysPermissionService.findListByWhere(null);
			permsList = new ArrayList<>(permissionList.size());
			for(SysPermission  menu : permissionList){
				permsList.add(menu.getDescription());
			}
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
//		sysUserService.queryAllMenuId(username);
        //查询用户信息
        SysUser user = sysUserService.queryByLoginName(username);
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定
        if("3".equals(user.getEnabledFlag())){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
	}

}
