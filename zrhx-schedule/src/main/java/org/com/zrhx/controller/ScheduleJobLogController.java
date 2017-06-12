package org.com.zrhx.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.com.zrhx.model.ScheduleJobLog;
import org.com.zrhx.service.ScheduleJobLogService;
import org.com.zrhx.utill.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: ScheduleJobLogController
 * @Description: 定时任务日志
 * @author: gs
 * @date: 2017/6/12 9:27
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows, ScheduleJobLog
			params) {
		PageInfo<ScheduleJobLog> pageInfo = scheduleJobLogService.findPageListByWhere(page, rows, params);
		return R.page(pageInfo.getSize(),pageInfo.getList());
	}
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") String logId){
		ScheduleJobLog log = scheduleJobLogService.findById(logId);
		
		return R.ok().put("log", log);
	}
}
