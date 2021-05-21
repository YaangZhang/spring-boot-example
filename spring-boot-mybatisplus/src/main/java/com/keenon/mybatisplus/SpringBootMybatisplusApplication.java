package com.keenon.mybatisplus;

import com.keenon.mybatisplus.entity.UserEntity;
import com.keenon.mybatisplus.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@MapperScan("com.keenon.mybatisplus.mapper")
@RestController
public class SpringBootMybatisplusApplication {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("list")
    public List<UserEntity> getList(){
        System.out.println("=== list ===");
        return userMapper.selectList(null);
    }

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisplusApplication.class, args);
    }

}
