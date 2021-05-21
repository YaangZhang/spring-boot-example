package com.sto.websocket.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

/**
 * @author wangx
 * @date 2020/5/26 17:32
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * redisTemplate相关配置
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        // 创建一个模板类
        RedisTemplate<String, String> template = new RedisTemplate<>();
        //  配置连接工厂
        template.setConnectionFactory(factory);

        //使用FastJson2JsonRedisSerializer来序列化和反序列化redis的value值
        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        //  指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        // 解决jackson2无法反序列化LocalDateTime的问题
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        // 设置key value的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        //  设置hash  key  和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

}
