package com.sto;

import com.sto.model.User;
import com.sto.repository.UserRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test()  {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
            String format = sdf.format(date);
            System.out.println("format :"+format);
        System.out.println("formattedDate = "+formattedDate);

        userRepository.save(new User("aa","aa@126.com","aa","aa123456",format));
        userRepository.save(new User("bb","bb@126.com","bb","bb123456",format));
        userRepository.save(new User("cc","cc@126.com","cc","cc123456",format));

        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            System.out.println("user : "+user);
        });

        int aaUpdate = userRepository.modifyById("aaUpdate", 1L);
        System.out.println("aaUpdate :"+aaUpdate);
        Optional<User> user1 = userRepository.findById(1l);
        System.out.println("user1 = "+user1 );

        List<User> byPassWord = userRepository.findByPassWord("cc123456");
        List<User> byNickName = userRepository.findByNickName("bb");
        System.out.println("byPassWord = "+byPassWord);
        System.out.println("byNickName = "+byNickName);
//        Assert.assertEquals(3,userRepository.findAll().size());
//        Assert.assertEquals("bb",userRepository.findByUserNameOrEmail("bb","bb@126.com").getNickName());
//        userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void testBaseQuery() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            System.out.println("user : "+user);
        });
        userRepository.findById(1l);
//        userRepository.save(user);
//        userRepository.delete(user);
        userRepository.count();
        userRepository.existsById(1l);
        // ...
    }

    @Test
    public void testQuery() {
//        userRepository.findByUserName();
        userRepository.findById(1l);
//        userRepository.save(user);
//        userRepository.delete(user);
        userRepository.count();
        userRepository.existsById(1l);
        // ...
    }

    @Test
    public void testPageQuery()  {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println("format :"+format);
        userRepository.save(new User("aa","aa@126.com","aa","aa123456",format));
        userRepository.save(new User("bb","bb@126.com","bb","bb123456",format));
        userRepository.save(new User("cc","cc@126.com","cc","cc123456",format));

        int page=0,size=2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> users = userRepository.findALL(pageable);
        long totalElements = users.getTotalElements();
        int totalPages = users.getTotalPages();
        System.out.println(" page : "+totalElements +"  ----   "+totalPages);
        System.out.println(users.toString());
        users.forEach(user -> {
            System.out.println("user : "+user);
        });
        Page<User> byNickName = userRepository.findByNickName("aa", pageable);
        System.out.println(" page : "+byNickName.getTotalElements() +"  ----   "+byNickName.getTotalPages());
        System.out.println("byNickName ="+byNickName);
        byNickName.forEach(user -> {
            System.out.println("user : "+user);
        });
    }

}