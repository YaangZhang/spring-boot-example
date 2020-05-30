package com.sto.mongo.model.repository;

import com.sto.mongo.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhy
 * @create 2019-11-27-17:01
 */
public interface UserReporitory extends MongoRepository<UserInfo, String> {

     public UserInfo findByUserName(String userName, String collection);

    Page<UserInfo> findAll(Pageable var1);
}
