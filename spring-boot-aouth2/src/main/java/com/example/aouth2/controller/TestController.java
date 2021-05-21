package com.example.aouth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/4/27
 * @Author by zhangy
 */
@RestController
public class TestController {

    @RequestMapping("/abc")
    public String index() {
        return "Hello abc";
    }

}
