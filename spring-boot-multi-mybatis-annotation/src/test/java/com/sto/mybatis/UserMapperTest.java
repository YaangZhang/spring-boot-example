package com.sto.mybatis;

import com.sto.mybatis.enums.UserSexEnum;
import com.sto.mybatis.mapper.test1.User1Mapper;
import com.sto.mybatis.mapper.test2.User2Mapper;
import com.sto.mybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsert() throws Exception {
        user1Mapper.insert(new User("aa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
    }

    @Test
    public void getAll() throws Exception {
        List<User> userList = user1Mapper.getAll();
        userList.forEach(user -> {
            System.out.println("user1 = "+user);
        });
        System.out.println("6………………………………………………");
        List<User> userList2 = user2Mapper.getAll();
        userList2.forEach(user -> {
            System.out.println("user2 = "+user);
        });
    }

  @Test
    public void getOnel() throws Exception {
        User user = user1Mapper.getOne(3L);
        System.out.println("user1 = "+user);

        System.out.println("6………………………………………………");
        User user2 = user2Mapper.getOne(3L);
        System.out.println("user2 = "+user2);
    }


}