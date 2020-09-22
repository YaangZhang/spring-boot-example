package com.sto.quartz.service;

import com.sto.quartz.model.DataGridResult;
import com.sto.quartz.model.TaskDO;
import com.sto.quartz.model.query.TaskQuery;
import org.quartz.SchedulerException;

/**
 * jobService
 * @author 小卖铺的老爷爷
 * @date 2018年6月23日
 * @website www.laoyeye.net
 */
public interface TaskService {
    
    TaskDO get(Long id);
    
    DataGridResult list(TaskQuery query);
    
    int save(TaskDO taskScheduleJob);
    
    int update(TaskDO taskScheduleJob);
    
    int remove(Long id);
    
    int removeBatch(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;
    
    void run(TaskDO scheduleJob) throws SchedulerException;
}
