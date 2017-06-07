package org.com.zrhx.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.com.zrhx.annotation.SysLogAnnotation;
import org.com.zrhx.model.ScheduleJobConfig;
import org.com.zrhx.service.ScheduleJobService;
import org.com.zrhx.utill.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Title: ScheduleJobController
 * @Description: 定时任务
 * @author: gs
 * @date: 2017/6/6 17:16
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController extends BaseController<ScheduleJobController> {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public R list(@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows,ScheduleJobConfig params){
		//查询列表数据
//		Query query = new Query(params);
//		List<ScheduleJobEntity> jobList = scheduleJobService.queryList(query);
//		int total = scheduleJobService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
//
//		return R.ok().put("page", pageUtil);
		PageInfo<ScheduleJobConfig> pageInfo = scheduleJobService.findPageListByWhere(page,rows,params);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public R info(@PathVariable("jobId") String jobId){
		ScheduleJobConfig schedule = scheduleJobService.findById(jobId);
		
		return R.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLogAnnotation("保存定时任务")
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public R save(@RequestBody ScheduleJobConfig scheduleJob){

		scheduleJobService.insert(scheduleJob,false);
		
		return R.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLogAnnotation("修改定时任务")
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public R update(@RequestBody ScheduleJobConfig scheduleJob){
		scheduleJobService.update(scheduleJob,false);
		
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLogAnnotation("删除定时任务")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public R delete( List<ScheduleJobConfig> jobIds){
		scheduleJobService.deleteByIds(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLogAnnotation("立即执行任务")
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody List<String> jobIds){
		scheduleJobService.run(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLogAnnotation("暂停定时任务")
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody List<String> jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLogAnnotation("恢复定时任务")
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody List<String> jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.ok();
	}

}
