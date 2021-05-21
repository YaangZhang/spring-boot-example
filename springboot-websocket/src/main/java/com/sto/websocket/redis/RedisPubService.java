package com.sto.websocket.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPubService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 向通道发送消息的方法
     * @param channel
     * @param message
     */
    public void sendChannelMess(String channel, String message) {
        try {
            stringRedisTemplate.convertAndSend(channel, message);
            simpMessagingTemplate.convertAndSend(channel, message+" _simpMessagingTemplate");
            //latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}