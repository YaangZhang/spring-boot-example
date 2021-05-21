package com.sto.stomp.service;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @功能描述 TODO
 * @创建时间 2020/10/21 15:09
 **/
@EnableScheduling //开启定时器功能
@Component
public class MessageSender {
    @Autowired
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate = 20000) //间隔 通过StringRedisTemplate对象向redis消息队列频道发布消息
    public void sendTopicMessage(){
        System.out.println("收到信息，向redis广播");
        stringRedisTemplate.convertAndSend("Mytopic","Mytopic:时间"+ DateUtil.now());
    }

}