package org.com.zrhx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.com.zrhx.model.SysPermission;
import org.com.zrhx.service.impl.SysPermissionServiceImpl;
import org.com.zrhx.utill.Constants;
import org.com.zrhx.utill.R;
import org.com.zrhx.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Title: SysPermissionController
 * @Description:  系统权限
 * @author: gs
 * @date: 2017/6/7 16:54
 */
@RestController
@RequestMapping("/sys/menu")
public class SysPermissionController extends BaseController<SysPermissionController> {
	@Autowired
	private SysPermissionServiceImpl sysPermissionService;

	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public R list(@RequestParam Map<String, Object> params) {
//		//查询列表数据
//		Query query = new Query(params);
//		List<SysMenuEntity> menuList = sysMenuService.queryList(query);
//		int total = sysMenuService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());
//
		return R.ok();
	}


	/**
	 * 用户权限列表（菜单权限）
	 */
	@RequestMapping("/user")
	public R user() {
		String userId = ShiroUtils.getUserId();
		if (Constants.ADMINISTRATOR_USERID.equals(userId)){
			List<SysPermission> menuList = sysPermissionService.findListByWhere(null);
			return R.ok().put("menuList", menuList);
		}else{
			List<SysPermission> menuList = sysPermissionService.queryUserList(ShiroUtils.getUserId());
			return R.ok().put("menuList", menuList);
		}



	}

}
