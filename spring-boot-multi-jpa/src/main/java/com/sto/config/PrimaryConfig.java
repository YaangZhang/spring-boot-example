package com.sto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement //启用事务
//最后一步最为关键，将我们在类中配置好的 EntityManager 和事务信息注入到对应数据源的 repository 目录下，这样此目录下的 repository 就会拥有对应数据源和事务的信息。
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "com.sto.repository.test1" })//设置dao（repo）所在位置
public class PrimaryConfig {
//    将第一个数据源和 JPA 配置信息注入到类中
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    /**
     * LocalEntityManagerFactoryBean 负责创建一个适合于仅使用 JPA 进行数据访问的环境的 EntityManager，
     * 构建的时候需要指明提示实体类的包路径、数据源和 JPA 配置信息。
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(vendorProperties)
                .packages("com.sto.model") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }


    /**
     * 利用上面的 entityManagerFactoryPrimary() 方法构建好最终的 EntityManager。
     *
     * EntityManager 是 JPA 中用于增、删、改、查的接口，它的作用相当于一座桥梁，
     * 连接内存中的 Java 对象和数据库的数据存储。使用 EntityManager 中的相关接口对数据库实体进行操作的时候，
     * EntityManager 会跟踪实体对象的状态，并决定在特定时刻将对实体的操作映射到数据库操作上面。
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }



//    同时给数据源添加上 JPA 事务。
    @Bean(name = "transactionManagerPrimary")
    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }



}