package com.keenon.springbootsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.keenon.springbootsecurity.mapper")
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

}
