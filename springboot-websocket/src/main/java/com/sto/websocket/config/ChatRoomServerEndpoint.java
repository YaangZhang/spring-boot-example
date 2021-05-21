package com.sto.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
/**
 * 聊天室
 */
@RestController
@ServerEndpoint("/chat-room/{username}") //代表监听此地址的 WebSocket 信息
public class ChatRoomServerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    /**
     * @OnOpen 注解和前端的 onopen 事件一致，表示用户建立连接时触发
     * 用户登录聊天室时，将用户信息添加到 ONLINE_USER_SESSIONS 中，同时通知聊天室中的人
     * @param name
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("username") String name, Session session){
        WebSocketUtils.ONLINE_USER_SESSIONS.put(name, session);
        String message = "欢迎用户 ["+ name +"] 来到聊天室！";
        logger.info("用户 "+name+"登录");
        WebSocketUtils.sendMessageAll(message);
    }

    /**
     * @OnMessage 监听发送消息的事件
     * 当聊天室某个用户发送消息时，将此消息同步给聊天室所有人。
     * @param username
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        logger.info("发送消息："+message);
        WebSocketUtils.sendMessageAll("用户[" + username + "] : " + message);
    }

    /**
     * @OnClose 监听用户断开连接事件
     * 当用户离开聊天室后，需要将用户信息从 ONLINE_USER_SESSIONS 移除，并且通知到在线的其他用户
     * @param username
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //当前的Session 移除
        WebSocketUtils.ONLINE_USER_SESSIONS.remove(username);
        //并且通知其他人当前用户已经离开聊天室了
        WebSocketUtils.sendMessageAll("用户[" + username + "] 已经离开聊天室了！");
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onClose error",e);
        }
    }

    /**
     * 当 WebSocket 连接出现异常时，触发 @OnError 事件，
     * 可以在此方法内记录下错误的异常信息，并关闭用户连接
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onError excepiton",e);
        }
        logger.info("Throwable msg "+throwable.getMessage());
    }

}
