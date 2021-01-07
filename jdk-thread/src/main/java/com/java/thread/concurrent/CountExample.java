package com.java.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhy
 * @create 2020-11-30-21:16
 */
public class CountExample {

    private  static int threadTotal = 200;
    private  static int clientTotal = 5000;

    private  static int count = 0;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            exec.execute(() ->{
                try {
//                    semaphore.acquire();
                    add();
//                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
        System.out.println("count: "+ count);
    }

    private static void add(){
        count++;
    }
}
