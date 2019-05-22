package com.sto.springbootjdbc;

import com.sto.springbootjdbc.modle.User;
import com.sto.springbootjdbc.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void testSave() {
        User user =new User("simple","123456","MAN");

        userRepository.save(user,secondaryJdbcTemplate);
        userRepository.save(user,primaryJdbcTemplate);
    }

    @Test
    public void testUpdate() {
        User user =new User("neo","123456","WOMAN");
        user.setId(0L);
        userRepository.update(user,primaryJdbcTemplate);
        userRepository.update(user,secondaryJdbcTemplate);
    }

    @Test
    public void testQueryOne()  {
        User user1=userRepository.findById(1L, primaryJdbcTemplate);
        User user2=userRepository.findById(1L, secondaryJdbcTemplate);
        System.out.println("user1 == "+user1.toString());
        System.out.println("user2 == "+user2.toString());
    }

    @Test
    public void testQueryAll()  {
        List<User> users1=userRepository.findALL(primaryJdbcTemplate);
        List<User> users2=userRepository.findALL(secondaryJdbcTemplate);
        for (User user:users1){
            System.out.println("user1 == "+user.toString());
        }
        for (User user:users2){
            System.out.println("user2 == "+user.toString());
        }
    }

}