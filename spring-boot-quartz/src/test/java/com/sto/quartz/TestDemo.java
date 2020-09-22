package com.sto.quartz;

import com.sto.quartz.mapper.TaskMapper;
import com.sto.quartz.model.TaskDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testList(){
        List<TaskDO> list = taskMapper.list();
        System.out.println(list);
    }
}