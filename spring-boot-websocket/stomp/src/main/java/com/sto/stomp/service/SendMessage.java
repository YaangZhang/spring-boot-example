package com.sto.stomp.service;

import com.sto.stomp.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @功能描述 消息发送
 * @创建时间 2020/10/21 15:23
 **/
@Service
public class SendMessage {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void send(String message){
        System.out.println("监听到信息 "+message);
    	ChatMessage chatMessage=new ChatMessage();
        chatMessage.setContent(message);
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender("监听者广播消息");
        System.out.println(chatMessage);
        simpMessagingTemplate.convertAndSend("/topic/public",chatMessage);
    }
}