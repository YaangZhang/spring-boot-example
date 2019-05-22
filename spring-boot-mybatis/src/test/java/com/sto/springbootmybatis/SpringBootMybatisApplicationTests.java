package com.sto.springbootmybatis;

import com.sto.springbootmybatis.enums.UserSexEnum;
import com.sto.springbootmybatis.mapper.UserMapper;
import com.sto.springbootmybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser()  {
        //增加
//        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        //删除
//        userMapper.delete(2l);
        User user = userMapper.getOne(1l);
//        user.setNikeName("smile");
//        //修改
//        userMapper.update(user);
//        //查询
//        List<User> users = userMapper.getAll();
//        users.forEach(user -> {
            System.out.println("user = "+user);
//        });
    }

    @Test
    public void contextLoads() {
    }

}
