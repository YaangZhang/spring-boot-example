package com.keenon.mybatisplus.service;

import com.keenon.mybatisplus.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhy
 * @since 2021-04-16
 */
public interface UserService extends IService<UserEntity> {

    public UserEntity getUserByName(String name);

    public UserEntity getUserByEmail(String name);
}
