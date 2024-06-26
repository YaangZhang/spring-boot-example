package com.sto.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot 2.0!";
    }

    /**
     * http://localhost:8071/hi?name=rose
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi", params = {"name"})
    public String hi(String name) {
        return "Hello Spring Boot 2.0! "+name;
    }

 @RequestMapping(value = "/hi", params = {"name", "age"})
    public String hi(String name, int age) {
        return age+"  Hello Spring Boot 2.0! "+name;
    }

    /**
     * http://localhost:8071/name?userName=jack
     * @param name
     * @return
     */
    @RequestMapping("/name")
    public String name(@RequestParam(name = "userName") String name) {
        return "Hello Spring Boot 2.0! "+name;
    }

    /**
     * http://localhost:8071/web/job
     * @PathVariable 绑定URI模板变量值,用来获得请求url中的动态参数的
     * @param name
     * @return
     */
    @RequestMapping("/web/{userName}")
    public String web(@PathVariable(value = "userName") String name) {
        return "Hello Spring Boot 2.0! "+name;
    }


}