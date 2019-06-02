package com.sto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//添加 SpringBootServletInitializer 是为了支持将项目打包成独立的 war 在 Tomcat 中运行的情况
public class SpringBootJpaThymeleafApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootJpaThymeleafApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaThymeleafApplication.class, args);
    }

}
