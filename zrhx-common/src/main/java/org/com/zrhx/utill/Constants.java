package org.com.zrhx.utill;

/**
 *  配置数据
 * @author gs
 *
 */
public final class Constants {
	public static final String JSON_LIST = "jsonlist";
	public static final String JSON = "json";
	/** total键 存放总记录数，必须的 ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度 */
	public static final String TOTALCOUNT = "total";
	/** rows键 存放每页记录 list */
	public static final String DATA = "rows";
	
	public static final int PAGESIZE = 10;
	
	public static final String SUCCESS = "success";
	public static final String MESSAGE = "message";

	public static final String MENU = "menu";

	// 软件版本
	public static final String VSERION = "V 1.0";
	// SESSION名称
	public static final String USER_SESSION_KEY = "user_session";
	// 超级管理员
	public static final String ADMINISTRATOR_LOGINNAME = "admin";
	// 超级管理员
	public static final String ADMINISTRATOR_USERID = "1";
	// 菜单
	public static final String MENU_LIST = "menuList";
	/****机构*****/
	public static final String ORG_LIST = "orgList";
	/****角色*****/
	public static final String ROLE_LIST = "roleList";	
	// 权限
	public static final String PERMISSION_LIST = "permissionList";

	public static final String UPLOAD_PATH = "upload";

	/**成功**/
	public static final String SUCCESS_CODE = "0";
	/**失败**/
	public static final String ERROR_CODE = "1";

	/**
	 * 菜单类型
	 *
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
	public enum MenuType {
		/**
		 * 目录
		 */
		CATALOG(0),
		/**
		 * 菜单
		 */
		MENU(1),
		/**
		 * 按钮
		 */
		BUTTON(2);

		private int value;

		private MenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 定时任务状态
	 *
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年12月3日 上午12:07:22
	 */
	public enum ScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(0),
		/**
		 * 暂停
		 */
		PAUSE(1);

		private int value;

		private ScheduleStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
