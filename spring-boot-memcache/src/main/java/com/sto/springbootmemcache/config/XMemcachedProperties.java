package com.sto.springbootmemcache.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "memcached")
@Setter
@Getter
public class XMemcachedProperties {

    private String servers;
    private int poolSize;
    private long opTimeout;


}
