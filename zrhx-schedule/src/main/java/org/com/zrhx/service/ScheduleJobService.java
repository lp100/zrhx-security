package org.com.zrhx.service;

import org.com.zrhx.model.ScheduleJobConfig;

import java.util.List;
import java.util.Map;

/**
 * @Title: ScheduleJobService
 * @Description: 定时任务
 * @author: gs
 * @date: 2017/6/6 17:59
 */
public interface ScheduleJobService extends  BaseService<ScheduleJobConfig> {
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatchStatus(List<String> jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(List<String> jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(List<String> jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(List<String> jobIds);
}
