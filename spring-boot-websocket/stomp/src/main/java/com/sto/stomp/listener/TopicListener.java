package com.sto.stomp.listener;

import com.sto.stomp.service.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @功能描述 监听消息
 * @创建时间 2020/10/21 15:07
 **/
@Component
public class TopicListener implements MessageListener {

    @Autowired
    private SendMessage sendMessage;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("我是Topic监听 ，收到redis广播信息：" + message.toString());
        sendMessage.send(message.toString());
    }
}