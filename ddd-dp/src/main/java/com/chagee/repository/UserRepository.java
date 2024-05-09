package com.chagee.repository;

import com.chagee.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User save(User user){
        return user;
    }
}
