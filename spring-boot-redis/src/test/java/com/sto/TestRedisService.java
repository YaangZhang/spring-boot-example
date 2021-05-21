package com.sto;

import com.sto.model.User;
import com.sto.redis.RedisService2;
import com.sto.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {

	@Autowired
    private RedisService redisService;

	@Autowired
	private RedisService2 redisService2;

    @Test
    public void testXml() throws Exception {
        boolean b = redisService2.setSubjectInfo("1234567890");
        System.out.println(b);
        String info = redisService2.getSubjectInfo("");
        System.out.println(info);
    }
 @Test
    public void testString() throws Exception {
        redisService.set("HELLO", "ityouknow");
     Object neo = redisService.get("neonn");
     Assert.assertEquals("ityouknow", neo);
    }

    @Test
    public void testZset() throws Exception {
        Set<Object> objectSet = redisService.zrange("gemini:program:asset:1:list", 1, 11);
        System.out.println(objectSet);
//        Assert.assertEquals("ityouknow", redisService.get("neo"));
    }

    @Test
    public void testObj() throws Exception {
        User user=new User( "smile", "youknow", "know","2020");
        redisService.set("user2",user);
        User userR=(User) redisService.get("user2");
        System.out.println("userR== "+userR.toString());
    }


}