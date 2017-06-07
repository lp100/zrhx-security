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
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:39:52
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
//		//查询列表数据
//		Query query = new Query(params);
//		List<ScheduleJobLog> jobList = scheduleJobLogService.queryList(query);
//		int total = scheduleJobLogService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
//
//		return R.ok().put("page", pageUtil);
//	}

		//查询列表数据
//		Query query = new Query(params);
//		List<ScheduleJobEntity> jobList = scheduleJobService.queryList(query);
//		int total = scheduleJobService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
//
//		return R.ok().put("page", pageUtil);
		PageInfo<ScheduleJobLog> pageInfo = scheduleJobLogService.findPageListByWhere(page, rows, params);
		return R.ok().put("page", pageInfo);
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
