package com.sto.springbootmybatis.controller;

import com.sto.springbootmybatis.mapper.UserMapper;
import com.sto.springbootmybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/users")
    public List<User> getAll(){
        List<User> users = userMapper.getAll();
        return users;
    }

    @RequestMapping("/user/{id}")
    public User getOne(@PathVariable("id")Long id){
        User user = userMapper.getOne(id);
        return user;
    }


}
