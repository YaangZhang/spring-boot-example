package com.keenon.springbootsecurity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "测试接口", tags = "用户管理相关的接口", description = "用户测试接口")
public class TestController {

    @GetMapping("/get")
    @ApiOperation("测试查询接口")
    public String test1(String name){
        return "hello " + name;
    }
}
