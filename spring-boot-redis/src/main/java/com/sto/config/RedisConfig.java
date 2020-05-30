package com.sto.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.lang.reflect.Method;

@Configuration
@EnableCaching //@EnableCaching 来开启缓存。
@ImportResource(locations = {"classpath:applicationContext-Data.xml"})
public class RedisConfig extends CachingConfigurerSupport {

    //配置主键的生产策略 KeyGenerator，如不配置会默认使用参数名作为主键。
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}