package com.sto.repository;

import com.sto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创建的 Repository 只要继承 JpaRepository 即可
 * 可以根据方法名自动生产 SQL，比如 findByUserName 会自动生产一个以 userName 为参数的查询方法，
 * 比如 findAll 会自动查询表里面的所有数据等
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username,String email);
}