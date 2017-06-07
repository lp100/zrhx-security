package org.com.zrhx.mapper;

import org.com.zrhx.annotation.MyBatisDao;
import org.com.zrhx.model.ScheduleJobConfig;
/**
 * @Title: ScheduleJobConfigMapper
 * @Description: 定时任务
 * @author: gs
 * @date: 2017/6/7 9:54
 */
@MyBatisDao
public interface ScheduleJobConfigMapper extends BaseDao<ScheduleJobConfig> {
    /**
     * 批量更新状态 -
     * @param scheduleJobConfig
     * @return
     */
    int updateBatchStatus(ScheduleJobConfig scheduleJobConfig);
}