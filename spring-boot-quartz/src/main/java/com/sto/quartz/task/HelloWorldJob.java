package com.sto.quartz.task;

import com.alibaba.fastjson.JSON;
import com.sto.quartz.model.TaskDO;
import com.sto.quartz.model.TaskInfo;
import com.sto.quartz.push.PushMessageToAppDemo;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@DisallowConcurrentExecution //作业不并发
@Component
public class HelloWorldJob implements Job{

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        
        System.out.println("欢迎使用yyblog,这是一个定时任务  --小卖铺的老爷爷!"+ new Date());
        JobDataMap jobDataMap = arg0.getJobDetail().getJobDataMap();
        Object o = jobDataMap.get(this.getClass().getName());

        TaskDO taskDO = JSON.parseObject(o.toString(), TaskDO.class);
        System.out.println(taskDO);
        PushMessageToAppDemo.pushMessageToApp("标题", "内容你好！", "1234567879908");
        
    }

}