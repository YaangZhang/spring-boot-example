package com.sto.controller;

import com.sto.mapper.UserMapper;
import com.sto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
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
