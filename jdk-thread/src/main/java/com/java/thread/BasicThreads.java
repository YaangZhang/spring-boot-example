package com.java.thread;

/**
 * @author zhy
 * @create 2020-05-19-9:00
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("taiting for Liftoff!");
    }
}
