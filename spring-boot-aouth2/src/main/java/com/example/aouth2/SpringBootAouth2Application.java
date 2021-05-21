package com.example.aouth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SpringBootAouth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAouth2Application.class, args);
    }

}
