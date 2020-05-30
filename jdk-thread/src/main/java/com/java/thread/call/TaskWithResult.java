package com.java.thread.call;

import java.util.concurrent.Callable;

/**
 * @author zhy
 * @create 2020-05-20-22:45
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }

}
