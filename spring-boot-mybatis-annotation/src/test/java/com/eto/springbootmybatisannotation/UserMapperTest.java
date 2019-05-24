package com.eto.springbootmybatisannotation;

import com.eto.springbootmybatisannotation.enums.UserSexEnum;
import com.eto.springbootmybatisannotation.mapper.UserMapper;
import com.eto.springbootmybatisannotation.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testInsert() throws Exception {
//        userMapper.insert(new User("ABCDEFG", "a123456", UserSexEnum.MAN));
//        userMapper.insert(new User("GFEDCBA", "b123456", UserSexEnum.WOMAN));
        List<User> userList = userMapper.getAll();
        userList.forEach(user -> {
            System.out.println("user = "+user);
        });
//        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
    }
      @Test
    public void getListByUserSex() throws Exception {
        userMapper.delete(5L);
        userMapper.delete(6L);
//          User one = userMapper.getOne(8L);
//          one.setNikeName("昵称走一波");
//          one.setUserSex(UserSexEnum.MAN);
//          userMapper.update(one);
          List<User> manList = userMapper.getListByUserSex("MAN");
          manList.forEach(user -> {
              System.out.println("user = "+user);
          });
    }

   @Test
    public void getListByUserSexAndName() throws Exception {
       Map param=  new HashMap();
       param.put("username","aa");
       param.put("user_sex","MAN");
       List<User> manList = userMapper.getListByNameAndSex(param);
          manList.forEach(user -> {
              System.out.println("user = "+user);
          });
    }


}