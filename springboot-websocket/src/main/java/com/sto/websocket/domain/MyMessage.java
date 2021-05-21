package com.sto.websocket.domain;

import lombok.Data;

/**
 * @Classname MyMessage
 * @Description TODO
 * @Date 2021/5/19
 * @Author by zhangy
 */
@Data
public class MyMessage {

    private String action;
    private String userId;
    private String message;
    private String sn;
}
