package com.sto.websocket.domain;

import lombok.Data;

/**
 * @Classname Message
 * @Description TODO
 * @Date 2021/5/19
 * @Author by zhangy
 */
@Data
public class Message {
    private String userId;
    private String toUserId;
    private String contentText;
}
