package com.sto.locker;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {
 private static final String LOCK_SUCCESS = "OK";    
 private static final String SET_IF_NOT_EXIST = "NX";    
 private static final String SET_WITH_EXPIRE_TIME = "PX";    
 /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
/**
 * 第一个为key，我们使用key来当锁，因为key是唯一的。
 * 第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
 * 第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
 * 第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
 * 第五个为time，与第四个参数相呼应，代表key的过期时间。
 */
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);       
         if (LOCK_SUCCESS.equals(result)) {           
          return true;
        }        
        return false;
    }

    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        /**
         * 第一行代码，一个简单的Lua脚本代码。第二行代码，将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。
         * eval()方法是将Lua代码交给Redis服务端执行。
         * Lua代码的功能：首先获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）。
         * 用Lua语言来确保上述操作是原子性的：在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
         */
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}