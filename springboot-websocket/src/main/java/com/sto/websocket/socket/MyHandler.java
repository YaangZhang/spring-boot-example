package com.sto.websocket.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linyun
 * @date 2018/9/13 下午3:26
 */
@Slf4j
@Service
public class MyHandler implements WebSocketHandler {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 为了保存在线用户信息，在方法中新建一个list存储一下【实际项目依据复杂度，可以存储到数据库或者缓存】
     */
    private final static ConcurrentHashMap<String, WebSocketSession> webSocketMap  = new ConcurrentHashMap<>();
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");

        log.info("{} 链接成功...... {}",  userName, this);
        if (userName != null) {
            webSocketMap.put(userName, session);
            JSONObject obj = new JSONObject();
            // 统计一下当前登录系统的用户有多少个
            obj.put("count", webSocketMap.size());
            users(obj);
            //session.sendMessage(new TextMessage(obj.toJSONString()));
            sendMessageToUsers(new TextMessage(obj.toJSONString()));
        }
    }
 
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("处理要发送的消息");
        JSONObject msg = JSON.parseObject(message.getPayload().toString());
        JSONObject obj = new JSONObject();
        if (msg.getInteger("type") == 1) {
            //给所有人
            log.info("发送给所有人:{} ", msg);
            obj.put("msg", msg.getString("msg"));
            sendMessageToUsers(new TextMessage(obj.toJSONString()));
        } else {
            //给个人
            String to = msg.getString("to");
            obj.put("msg", msg.getString("msg"));
            sendMessageToUser(to, new TextMessage(obj.toJSONString()));
        }
    }
 
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.info("链接出错，关闭链接......{}", exception);
        for (Map.Entry<String, WebSocketSession> entrySet : webSocketMap.entrySet()) {
            if (entrySet.getValue().equals(session)) {
                webSocketMap.remove(entrySet.getKey());
            }
        }
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("链接关闭......" + closeStatus.toString());
        for (Map.Entry<String, WebSocketSession> entrySet : webSocketMap.entrySet()) {
            if (entrySet.getValue().equals(session)) {
                webSocketMap.remove(entrySet.getKey());
            }
        }
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, WebSocketSession> entrySet : webSocketMap.entrySet()) {
            try {
                if (entrySet.getValue().isOpen()) {
                    entrySet.getValue().sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (Map.Entry<String, WebSocketSession> entrySet : webSocketMap.entrySet()) {
            if (entrySet.getKey().equals(userName)) {
                try {
                    if (entrySet.getValue().isOpen()) {
                        log.info("发送给 {} 信息:{} ",userName,  message.getPayload());
                        entrySet.getValue().sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
 
    /**
     * 将系统中的用户传送到前端
     *
     * @param obj
     */
    private void users(JSONObject obj) {
        List<String> userNames = new ArrayList<>();
        for (Map.Entry<String, WebSocketSession> entrySet : webSocketMap.entrySet()) {
            userNames.add(entrySet.getKey());
        }
        obj.put("users", userNames);
    }
}
