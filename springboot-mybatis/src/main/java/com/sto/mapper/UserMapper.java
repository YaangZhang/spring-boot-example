package com.sto.mapper;

import com.sto.model.User;
import com.sto.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper()
public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    List<User> getList(UserParam userParam);
    int getCount(UserParam userParam);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}