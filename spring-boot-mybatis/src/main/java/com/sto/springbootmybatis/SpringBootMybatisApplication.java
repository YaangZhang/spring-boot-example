package com.sto.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在启动类中添加对 Mapper 包扫描 @MapperScan，Spring Boot 启动的时候会自动加载包路径下的 Mapper
 *
 * 或者直接在 Mapper 类上面添加注解 @Mapper，建议使用上面那种，不然每个 mapper 加个注解也挺麻烦的。
 */
@SpringBootApplication
@MapperScan("com.sto.springbootmybatis.mapper")
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}
