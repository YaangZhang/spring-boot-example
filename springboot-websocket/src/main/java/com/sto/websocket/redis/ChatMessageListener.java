package com.sto.websocket.redis;

import com.alibaba.fastjson.JSONObject;
import com.sto.websocket.socket.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

/**
 * @Description 集群聊天消息监听器
 * @Author jie.zhao
 * @Date 2020/3/29 15:07
 */
@Component
public class ChatMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MyHandler myHandler;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<String> valueSerializer = redisTemplate.getStringSerializer();
        String value = valueSerializer.deserialize(message.getBody());
        String channel = valueSerializer.deserialize(message.getChannel());
        System.out.println("我监听到频道1的消息啦,消息是:" + message);
        System.out.println( channel);
        System.out.println( value);
        JSONObject obj = new JSONObject();
        obj.put("msg", value);
        myHandler.sendMessageToUser("jack", new TextMessage(obj.toJSONString()));

    }

}
