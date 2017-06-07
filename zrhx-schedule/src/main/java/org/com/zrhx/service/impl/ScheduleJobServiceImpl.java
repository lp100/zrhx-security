package org.com.zrhx.service.impl;

import org.com.zrhx.mapper.ScheduleJobConfigMapper;
import org.com.zrhx.model.ScheduleJobConfig;
import org.com.zrhx.service.Impl.BaseServiceImpl;
import org.com.zrhx.service.ScheduleJobService;
import org.com.zrhx.utill.Constants;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.com.zrhx.utils.ScheduleUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobConfig,ScheduleJobConfigMapper> implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobConfig> scheduleJobList = mapper.queryList(null);
		for(ScheduleJobConfig scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobID());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}


	@Override
	@Transactional(readOnly = false)
	public Integer insert(ScheduleJobConfig scheduleJob,boolean selectiveFlag) {
		scheduleJob.setCreateDate(new Date());
		scheduleJob.setStatus(Constants.ScheduleStatus.NORMAL.getValue());
		int i = mapper.insert(scheduleJob);
		if (i>0){
			ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
		}
        return  i;

    }

	@Override
	@Transactional(readOnly = false)
	public Integer update(ScheduleJobConfig scheduleJob,boolean selectiveFlag) {

		scheduleJob.setUpdateDate(new Date());
		int i = mapper.updateByPrimaryKey(scheduleJob);
		if (i>0){
			ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		}
		return  i;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer deleteByIds(List<ScheduleJobConfig> jobIds) {
    	//删除数据
		int i = mapper.deleteBatch(jobIds);
		if (i>0){
			for(ScheduleJobConfig jobId : jobIds){
				ScheduleUtils.deleteScheduleJob(scheduler, jobId.getJobID());
			}
		}
		return i;
	}

	@Override
	@Transactional(readOnly = false)
    public int updateBatchStatus(List<String> jobIds, int status){
		ScheduleJobConfig scheduleJobConfig  = new ScheduleJobConfig();
		scheduleJobConfig.setList(jobIds);
		scheduleJobConfig.setStatus(status);
    	return mapper.updateBatchStatus(scheduleJobConfig);
    }
    
	@Override
	@Transactional(readOnly = false)
    public void run(List<String> jobIds) {
    	for(String jobId : jobIds){
    		ScheduleUtils.run(scheduler, mapper.selectByPrimaryKey(jobId));
    	}
    }

	@Override
	@Transactional(readOnly = false)
    public void pause(List<String> jobIds) {
		int i = updateBatchStatus(jobIds, Constants.ScheduleStatus.PAUSE.getValue());
		if (i>0)
		for(String jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        

    }

	@Override
	@Transactional(readOnly = false)
    public void resume(List<String> jobIds) {
		int i = updateBatchStatus(jobIds, Constants.ScheduleStatus.NORMAL.getValue());
		if (i>0)
    	for(String jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    }
    
}
