package com.sto.springbootmemcache.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration //利用 @Configuration 注解，在启动时对 Memcached 进行初始化。
public class MemcachedBuilder {
    protected static Logger logger =  LoggerFactory.getLogger(MemcachedBuilder.class);
    @Resource
    private XMemcachedProperties xMemcachedProperties;

    /**
     * 在方法 getMemcachedClient() 添加 @Bean 注解，代表启动时候将方法构建好的实例注入到 Spring 容器中，
     * 后面在需要使用的类中，直接注入 MemcachedClient 即可。
     * @return
     */
    @Bean
    public MemcachedClient getMemcachedClient() {
        MemcachedClient memcachedClient = null;
        /**
         * 因为 XMemcachedClient 的创建有比较多的可选项，所以提供了一个 XMemcachedClientBuilder 类用于构建 MemcachedClient。
         * MemcachedClient 是主要接口，操作 Memcached 的主要方法都在这个接口，XMemcachedClient 是它的一个实现。
         */
        try {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getServers()));
            builder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
            builder.setOpTimeout(xMemcachedProperties.getOpTimeout());
            memcachedClient = builder.build();
        } catch (IOException e) {
            logger.error("inint MemcachedClient failed ",e);
        }
        return memcachedClient;
    }

}
