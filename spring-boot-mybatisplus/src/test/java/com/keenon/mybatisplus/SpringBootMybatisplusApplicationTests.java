package com.keenon.mybatisplus;

import com.keenon.mybatisplus.entity.UserEntity;
import com.keenon.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getList(){
        List<UserEntity> selectList = userMapper.selectList(null);
        selectList.forEach(System.out::print);
    }
    @Test
   public void contextLoads() {
    }

}
