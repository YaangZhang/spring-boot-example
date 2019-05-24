package com.sto;

import com.sto.model.User;
import com.sto.repository.UserRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test()  {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        System.out.println("formattedDate = "+formattedDate);

        userRepository.save(new User("aa","aa@126.com","aa","aa123456",formattedDate));
        userRepository.save(new User("bb","bb@126.com","bb","bb123456",formattedDate));
        userRepository.save(new User("cc","cc@126.com","cc","cc123456",formattedDate));

//        Assert.assertEquals(9,userRepository.findAll().size());
//        Assert.assertEquals("bb",userRepository.findByUserNameOrEmail("bb","cc@126.com").getNickName());
//        userRepository.delete(userRepository.findByUserName("aa1"));
    }

}