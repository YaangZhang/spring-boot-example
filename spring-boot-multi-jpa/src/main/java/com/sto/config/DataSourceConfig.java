package com.sto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfig {

//    加载第一个数据源
    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.test1")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

//    加载第二个数据源
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties("spring.datasource.test2")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

//    加载 JPA 的相关配置信息，JpaProperties 是 JPA 的一些属性配置信息，构建 LocalEntityManagerFactoryBean 需要参数信息注入到方法中。

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "vendorProperties")
    public Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }


}
