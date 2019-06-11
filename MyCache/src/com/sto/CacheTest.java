/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: CacheTest
 * Author:   thinkpad
 * Date:     2019-06-11 23:18
 * Description:  缓存工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-06-11.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 〈一句话功能简述〉<br> 
 * 〈 缓存工具类测试〉
 *
 * @author thinkpad
 * @create 2019-06-11
 * @since 1.0.0
 */
public class CacheTest {
    public static void main(String[] args) throws Exception{
        String key = "id";
        //不设置过期时间
//        System.out.println("***********不设置过期时间**********");
//        Cache.put(key, 123);
//        System.out.println("key :"+key+", value :"+Cache.get(key));
//        System.out.println("key:" + key + ", value :" + Cache.remove(key));
//        System.out.println("key:" + key + ", value :" + Cache.get(key));
//
//        //设置过期时间
//        System.out.println("\n***********设置过期时间**********");
//        Cache.put(key, "123456", 1000);
//        System.out.println("key:" + key + ", value:" + Cache.get(key));
//        Thread.sleep(2000);
//        System.out.println("key:" + key + ", value:" + Cache.get(key));
//
//        Cache.put(key, 100L, 1000);
//        System.out.println("map.size = "+Cache.size());

        test1(key);
        test2(key);

    }

    private static void test1(String key)throws Exception{
        /******************并发性能测试************/
        System.out.println("\n***********并发性能测试************");
        //创建有10个线程的线程池，将1000000次操作分10次添加到线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future[] futures = new Future[10];
        /********添加********/
        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                futures[i] =  executorService.submit(() -> {
                    for (int j=0; j < 100000; j++) {
                        CacheMap.put(Thread.currentThread().getId()+key+j, j, 300000);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                future.get();
            }
            System.out.printf("添加耗时：%dms\n", System.currentTimeMillis() - start);

        }
        /********查询********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        CacheMap.get(Thread.currentThread().getId() + key + i);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                future.get();
            }
            System.out.printf("查询耗时：%dms\n", System.currentTimeMillis() - start);
        }
        System.out.println("当前缓存容量：" + CacheMap.size());
    }

     private static void test2(String key)throws Exception{
        /******************并发性能测试************/
        System.out.println("\n***********并发性能测试************");
        //创建有10个线程的线程池，将1000000次操作分10次添加到线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future futures = null;
        /********添加********/
        {
            long start = System.currentTimeMillis();
                futures =  executorService.submit(() -> {
                    for (int j=0; j < 1000000; j++) {
                        CacheMap.put(Thread.currentThread().getId()+key+j, j, 300000);
                    }
                });

            //等待全部线程执行完成，打印执行时间
                futures.get();
            System.out.printf("添加耗时：%dms\n", System.currentTimeMillis() - start);

        }
        /********查询********/
        {
            long start = System.currentTimeMillis();
                futures = executorService.submit(() -> {
                    for (int i = 0; i < 1000000; i++) {
                        CacheMap.get(Thread.currentThread().getId() + key + i);
                    }
                });
            //等待全部线程执行完成，打印执行时间
                futures.get();
            System.out.printf("查询耗时：%dms\n", System.currentTimeMillis() - start);
        }
        System.out.println("当前缓存容量：" + CacheMap.size());
    }


}
