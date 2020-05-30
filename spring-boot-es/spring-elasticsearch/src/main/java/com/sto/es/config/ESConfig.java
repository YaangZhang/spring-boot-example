package com.sto.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author zhy
 * @create 2020-04-09-16:15
 */
@Configuration
public class ESConfig {

    @Bean
    public TransportClient client(){
        // 9300是es的tcp服务端口
        TransportAddress node = null;
        try{
            node = new InetSocketTransportAddress(
//                    InetAddress.getByName("127.0.0.1"), 9300);
//                    InetAddress.getByName("120.78.124.56"), 9300);
//                    InetAddress.getByName("10.10.42.198"), 8310);
                    InetAddress.getByName("47.100.105.14"), 9300);
        }catch(Exception e){
            System.out.println(e);
        }
        // 设置es节点的配置信息  client-transport-sniff="false"
        Settings settings = Settings.builder()
                .put("cluster.name", "my-cluster")
                .build();

        // 实例化es的客户端对象
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
