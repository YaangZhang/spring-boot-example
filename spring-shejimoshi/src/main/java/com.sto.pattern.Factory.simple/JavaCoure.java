package com.sto.pattern.Factory.simple;

/**
 * @author zhy
 * @create 2020-08-20-22:08
 */
public class JavaCoure implements ICoure {
    @Override
    public void record() {
        System.out.println("录制java课程");
    }

    public static void main(String[] args) {
        ICoure coure = new JavaCoure();
        coure.record();
    }
}
