package com.keenon.mybatisplus.controller;


import com.keenon.mybatisplus.entity.UserEntity;
import com.keenon.mybatisplus.mapper.UserMapper;
import com.keenon.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhy
 * @since 2021-04-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list1")
    public List<UserEntity> getList(){
        System.out.println("=== list ===");
        return userMapper.selectList(null);
    }

    @GetMapping("/list2")
    public List<UserEntity> getList2(){
        System.out.println("=== list ===");
        return userService.list(null);
    }

    @GetMapping("/name")
    public UserEntity getList2(String name){
        System.out.println("=== list ===");
        return userService.getUserByName(name);
    }


    @GetMapping("/email")
    public UserEntity getUserByEmail(String email){
        System.out.println("=== list ===");
        return userService.getUserByEmail(email);
    }


}
