package com.sto.stomp.entity;

import lombok.Data;

/**
 * @功能描述 TODO
 * @创建时间 2020/10/21 14:08
 **/
@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}