package com.sto;

import com.sto.model.User;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Test
    public void testString()  {
        redisTemplate.opsForValue().set("neo", "ityouknow222");
        Object neo = redisTemplate.opsForValue().get("neo");
        System.out.println("redisTemplate : "+neo);
        Assert.assertEquals("ityouknow222", redisTemplate.opsForValue().get("neo"));

//        redisTemplate.opsForValue().set("num", 11156);
        Object num = redisTemplate.opsForValue().get("num");
        System.out.println("redisTemplate num: "+num);
    }

    //操作实体
    @Test
    public void testObj(){
        User user=new User( "smile","2020", "youknow", "know");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.sto", user);
        User u=operations.get("com.sto");
        System.out.println("user: "+u);
    }

    //超时失效
    @Test
    public void testExpire() throws InterruptedException {
        User user=new User( "expire","2022", "youknow", "expire");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("expire", user,100,TimeUnit.MILLISECONDS);
        User u=operations.get("expire");
        System.out.println("user: "+u);
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }
    //删除数据
    @Test
    public void testDelete() {
        ValueOperations<String, String> operations=redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deletekey", "ityouknow");
        String deletekey = operations.get("deletekey");
        System.out.println("deletekey : "+deletekey);
        redisTemplate.delete("deletekey");
        boolean exists=redisTemplate.hasKey("deletekey");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    /**
     * Hash（哈希）
     * 一般我们存储一个键，很自然的就会使用 get/set 去存储，实际上这并不是很好的做法。
     * Redis 存储一个 key 会有一个最小内存，不管你存的这个键多小，都不会低于这个内存，因此合理的使用 Hash 可以帮我们节省很多内存。
     *
     * Hash Set 就在哈希表 Key 中的域（Field）的值设为 value。
     * 如果 Key 不存在，一个新的哈希表被创建并进行 Hset 操作；如果域（Field）已经存在于哈希表中，旧值将被覆盖
     */
    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","name","rose");
        String value=(String) hash.get("hash","name");
        System.out.println("hash value :"+value);
    }

    /**
     * List
     *     Redis List 的应用场景非常多，也是 Redis 最重要的数据结构之一。
     *     使用 List 可以轻松的实现一个队列，List 典型的应用场景就是消息队列，
     *     可以利用 List 的 Push 操作，将任务存在 List 中，然后工作线程再用 POP 操作将任务取出进行执行。
     */
    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
//        list.leftPush("list","it");
//        list.leftPush("list","you");
//        list.leftPush("list","know");
//        String value=(String)list.leftPop("list");
//        System.out.println("list value :"+value.toString());
        //在 list 列表里
        list.remove("list", 3,"it");
        List<String> values=list.range("list",0,2);
        for (String v:values){
            System.out.println("list range :"+v);
        }
    }

    /**
     * Redis Set : 可以自动排重
     *Set 提供了判断某个成员是否在一个 Set 集合内的重要接口，这个也是 List 所不能提供的
     * @Test
     */
    @Test
    public void testSet() {
        String key="set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,"it");
        set.add(key,"you");
        set.add(key,"you");
        set.add(key,"know");
        Set<String> values=set.members(key);
        for (String v:values){
            System.out.println("set value :"+v);
        }
    }

 @Test
    public void testStr(){
     String token = "testdemo11111111111119";
     Map userInfo = new HashMap();
     userInfo.put("number", "1");
     userInfo.put("account", "2");
     userInfo.put("username", "3");
     userInfo.put("clientip", "4");
     //将管理员信息保存到 redis：管理员编号、管理员账号、管理员客户端ip
     redisTemplate.opsForHash().putAll(token,userInfo);
    }

}   