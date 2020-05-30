package com.java.thread;

/**
 * @author zhy
 * @create 2020-05-19-9:03
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.out.println("taiting for Liftoff!");
        }
    }
}
