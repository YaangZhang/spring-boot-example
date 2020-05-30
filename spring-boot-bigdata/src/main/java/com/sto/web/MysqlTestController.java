/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: MysqlTestController
 * Author:   thinkpad
 * Date:     2019-06-13 0:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.web;
/**
 * Created by thinkpad on 2019-06-13.
 */

import com.sto.modle.User;
import com.sto.service.InsterService;
import com.sto.service.MultiInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-06-13
 * @since 1.0.0
 */

@RestController
public class MysqlTestController {

    @Autowired
    private MultiInsert multiInsert;
    @Autowired
    private InsterService insterService;

    @GetMapping(value = "mulTest")
    public String mulTest(Integer size, @RequestParam(defaultValue = "false") boolean sharding) {
        BlockingQueue<User> userQueue = multiInsert.initUserQueue(size);
        System.out.println("开始时间 ： "+new Date());
        long mulTime = multiInsert.processQueue(userQueue, sharding);
        return size + " 条记录插入，耗时 " + mulTime+" ms ,   速度 ："+(size / mulTime * 1000)+" 条/s";
    }


    @GetMapping(value = "insertTest")
    public String insertTest(@PathVariable int size,@PathVariable int batchSize, @RequestParam(defaultValue = "false") boolean sharding) {
        System.out.println("开始时间 ： "+ new Date());
        long start = System.currentTimeMillis();
        try {
            if (sharding) {
                insterService.testInsert(size);
            }else  insterService.testBatchInsert(size, batchSize);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - start;
        return size + " 条记录插入，耗时 " + time+" ms ,   速度 ："+(size / time * 1000)+" 条/s";
    }



}
