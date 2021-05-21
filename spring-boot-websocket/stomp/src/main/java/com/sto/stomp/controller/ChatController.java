package com.sto.stomp.controller;

import com.sto.stomp.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @功能描述 TODO
 * @创建时间 2020/10/21 14:10
 **/
@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * @MessageMapping 注解用于监听指定路径的客户端消息
     * @SendTo 注解则用于将服务端的消息发送给监听了该路径的客户端
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


}