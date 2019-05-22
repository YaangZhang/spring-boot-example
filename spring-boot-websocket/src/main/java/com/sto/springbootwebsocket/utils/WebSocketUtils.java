package com.sto.springbootwebsocket.utils;

//import org.springframework.boot.web.servlet.server.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtils {
    // 存储 websocket session
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    /**
     *
     * @param session 用户 session
     * @param message 发送内容
     */
    public static void sendMessage(Session session, String message){
        if (session == null) {
            return;
        }
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        if (basicRemote == null) {
            return;
        }
        try {
            basicRemote.sendText(message);
        } catch (IOException e) {
            logger.error("sendMessage IOException ",e);
        }
    }

    public static void sendMessageAll(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, message));
    }

}
