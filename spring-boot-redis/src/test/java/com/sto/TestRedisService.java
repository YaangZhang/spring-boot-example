package com.sto;

import com.sto.model.User;
import com.sto.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {
	@Autowired
	private RedisService redisService;

    @Test
    public void testString() throws Exception {
        redisService.set("neo", "ityouknow");
        Assert.assertEquals("ityouknow", redisService.get("neo"));
    }
    
    @Test
    public void testObj() throws Exception {
        User user=new User( "smile", "youknow", "know","2020");
        redisService.set("user2",user);
        User userR=(User) redisService.get("user2");
        System.out.println("userR== "+userR.toString());
    }


}