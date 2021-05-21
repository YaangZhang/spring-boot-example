package com.sto.stomp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


/**
 * @功能描述 STOMP服务配置实现
 * @创建时间 2020/10/21 14:05
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MyChannelInterceptor myChannelInterceptor;

    private static long HEART_BEAT = 10000;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
//                .addInterceptors(new SessionAuthHandshakeInterceptor())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //设置简单的消息代理器，它使用Memory（内存）作为消息代理器，
        //其中/user和/topic都是我们发送到前台的数据前缀。前端必须订阅以/user开始的消息（.subscribe()进行监听）。
        //setHeartbeatValue设置后台向前台发送的心跳，
        //注意：setHeartbeatValue这个不能单独设置，不然不起作用，要配合后面setTaskScheduler才可以生效。
        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
        te.setPoolSize(1);
        te.setThreadNamePrefix("wss-heartbeat-thread-");
        te.initialize();
        registry.enableSimpleBroker("/user", "/topic").setHeartbeatValue(new long[]{HEART_BEAT, HEART_BEAT}).setTaskScheduler(te);
        //客户端需要把消息发送到/message/xxx地址
        registry.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(myChannelInterceptor);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(500 * 1024 * 1024);
        registration.setSendBufferSizeLimit(1024 * 1024 * 1024);
        registration.setSendTimeLimit(200000);
    }

}