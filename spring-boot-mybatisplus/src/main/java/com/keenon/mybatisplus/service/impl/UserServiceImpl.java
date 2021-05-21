package com.keenon.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keenon.mybatisplus.entity.UserEntity;
import com.keenon.mybatisplus.mapper.UserMapper;
import com.keenon.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhy
 * @since 2021-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getList(){
        System.out.println("=== list ===");
        return userMapper.selectList(null);
    }

    @Override
    public UserEntity getUserByName(String name){
        return userMapper.getUserByName(name);
    }
     @Override
     public UserEntity getUserByEmail(String name){
        return userMapper.getUserByEmail(name);
    }

}
