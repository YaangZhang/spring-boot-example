package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhy
 * @create 2020-05-19-22:02
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i ++) {
            executorService.execute(new LiftOff());
        }
//        executorService.shutdown();
        System.out.println("==================================");
  ExecutorService executorService2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i ++) {
            executorService2.execute(new LiftOff());
        }
        executorService2.shutdown();

    }
}
