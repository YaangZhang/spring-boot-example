package com.sto.mapper.two;

import com.sto.model.User;
import com.sto.param.UserParam;

import java.util.List;

public interface User2Mapper {

    List<User> getAll();

    User getOne(Long id);

    List<User> getList(UserParam userParam);
    int getCount(UserParam userParam);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}