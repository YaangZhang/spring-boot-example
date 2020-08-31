package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhy
 * @create 2020-05-21-22:01
 */
public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            try {
                Thread.sleep(100);

                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException "+e);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i ++)
            executorService.execute(new SleepingTask());
            executorService.shutdown();

    }
}
