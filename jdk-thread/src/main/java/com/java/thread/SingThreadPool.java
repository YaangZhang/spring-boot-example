package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhy
 * @create 2020-05-19-22:17
 */
public class SingThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i ++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
