package com.sto.springbootmemcache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests {
    @Autowired
    private MemcachedClient memcachedClient;

    /**
     * 存储数据: set 方法，它有三个参数，
     * 第一个是存储的 key 名称，
     * 第二个是 expire 时间（单位秒），超过这个时间，memcached 将这个数据替换出去，0 表示永久存储（默认是一个月），
     * 第三个参数就是实际存储的数据，可以是任意的 Java 可序列化类型。
     *
     * 获取存储的数据是通过 get 方法，传入 key 名称即可；
     * 删除存储的数据，可以通过 delete 方法，它也是接受 key 名称作为参数。
     * @throws Exception
     */
    @Test
    public void testGetSet() throws Exception {
        boolean hello = memcachedClient.set("hello", 0, "Hello,xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
        memcachedClient.delete("hello");
    }

    /**
     * add 命令，用于将 value（数据值）存储在指定的 key（键）中。如果 add 的 key 已经存在，则不会更新数据（过期的 key 会更新），之前的值将仍然保持相同，并且将获得响应 NOT_STORED。
     * replace 命令，用于替换已存在的 key（键）的 value（数据值）。如果 key 不存在，则替换失败，并且将获得响应 NOT_STORED。
     * append 命令，用于向已存在 key（键）的 value（数据值）后面追加数据。
     * prepend 命令，用于向已存在 key（键）的 value（数据值）前面追加数据。
     * deleteWithNoReply 方法，这个方法删除数据并且告诉 Memcached，不用返回应答，因此这个方法不会等待应答直接返回，比较适合于批量处理。
     * @throws Exception
     */
    @Test
    public void testMore() throws Exception {
        if (!memcachedClient.set("hello", 0, "world")) {
            System.err.println("set error");
        }
        if (!memcachedClient.add("hello", 0, "dennis")) {
            System.err.println("Add error,key is existed");
        }
        if (!memcachedClient.replace("hello", 0, "dennis")) {
            System.err.println("replace error");
        }
        memcachedClient.append("hello", " good");
        memcachedClient.prepend("hello", "hello ");
        String name = memcachedClient.get("hello", new StringTranscoder());
        System.out.println(name);
        memcachedClient.deleteWithNoReply("hello");
    }

    /**
     * Incr 和 Decr : Incr 和 Decr 类似数据的增和减
     * Incr 和 Decr 都有三个参数的方法，第一个参数指定递增的 key 名称，第二个参数指定递增的幅度大小，第三个参数指定当 key 不存在的情况下的初始值，
     * 两个参数的重载方法省略了第三个参数，默认指定为 0。
     *
     * @throws Exception
     */
    @Test
    public void testIncrDecr() throws Exception {
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");
        System.out.println(memcachedClient.incr("Incr", 6, 12));
        System.out.println(memcachedClient.incr("Incr", 3));
        System.out.println(memcachedClient.incr("Incr", 2));
        System.out.println(memcachedClient.decr("Decr", 1, 6));
        System.out.println(memcachedClient.decr("Decr", 2));
    }

}