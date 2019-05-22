package com.sto;

import com.sto.enums.UserSexEnum;
import com.sto.mapper.UserMapper;
import com.sto.model.User;
import com.sto.param.UserParam;
import com.sto.result.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser()  {
        //增加
        userMapper.insert(new User("aa", "qazwsx", UserSexEnum.MAN));
        //删除
//        userMapper.delete(2l);
//        User user = userMapper.getOne(1l);
//        user.setNikeName("smile");
//        //修改
//        userMapper.update(user);
//        //查询
        List<User> users = userMapper.getAll();
        users.forEach(user -> {
        System.out.println("user = "+user);
        });
    }

    @Test
    public void testPage() {
        UserParam userParam=new UserParam();
        userParam.setUserSex("MAN");
        userParam.setCurrentPage(0);
        List<User> users=userMapper.getList(userParam);
        long count=userMapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        System.out.println(page);
    }

    @Test
    public void contextLoads() {
    }

}
