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
package com.sto;
/**
 * Created by thinkpad on 2019-06-13.
 */

import com.sto.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "mulTest")
    public String mulTest(Integer size, @RequestParam(defaultValue = "false") boolean sharding) {
        BlockingQueue<User> userQueue = initUserQueue(size);
        return size + " 条记录插入，耗时" + processQueue(userQueue, sharding);
    }

    private long processQueue(BlockingQueue<User> userQueue, boolean sharding) {
        int mulSize = 200;
        long beginTime = System.currentTimeMillis();
        try {
            if (sharding) {
                while (!userQueue.isEmpty()) {
                    User user = userQueue.take();
                    StringBuffer singleInserSql = new StringBuffer();
                    singleInserSql.append("INSERT INTO `user` (`id`, `user_name`, `pass_word`, `age`, `city`, `email`, `nick_name`, `reg_time`) VALUES (?,?,?,?,?,?,?,?)");
                    jdbcTemplate.update(singleInserSql.toString(), new Object[]{
                            user.getId(), user.getUserName(), user.getPassWord(), user.getAge(), user.getCity(), user.getEmail(), user.getNickName(), user.getRegTime() });
                }
            } else {
                String insertSqlStr = genMulFixSqlStr(mulSize);
                List<User> userList = new ArrayList<>();
                while (!userQueue.isEmpty()) {
                    User user = userQueue.take();
                    userList.add(user);
                    if (userList.size() == mulSize) {
                        Object[] params = getMulParams(userList, userList.size());
                        jdbcTemplate.update(insertSqlStr, params);
                        userList.clear();
                    }
                }
                //插入剩余的数据
                if (!userList.isEmpty()) {
                    String restInsertSql = genMulFixSqlStr(userList.size());
                    Object[] params = getMulParams(userList, userList.size());
                    jdbcTemplate.update(restInsertSql, params);
                    userList.clear();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - beginTime;
    }

    private Object[] getMulParams(List<User> userList, int mulSize) {
        Object[] params = new Object[mulSize * 8];
        for (int i = 0; i < mulSize; i++) {
            User userInfo = userList.get(i);
            params[8 * i] = userInfo.getId();
            params[8 * i + 1] = userInfo.getUserName();
            params[8 * i + 2] = userInfo.getPassWord();
            params[8 * i + 3] = userInfo.getAge();
            params[8 * i + 4] = userInfo.getCity();
            params[8 * i + 5] = userInfo.getEmail();
            params[8 * i + 6] = userInfo.getNickName();
            params[8 * i + 7] = userInfo.getRegTime();
        }
        return params;
    }

    private String genMulFixSqlStr(int mulSize) {
        StringBuffer mulInserSql = new StringBuffer();
        mulInserSql.append("INSERT INTO `user` (`id`, `user_name`, `pass_word`, `age`, `city`, `email`, `nick_name`, `reg_time`) VALUES (?,?,?,?,?,?,?,?)");
        for (int i = 1; i < mulSize; i++) {
            mulInserSql.append(",(?,?,?,?,?,?,?,?)");
        }
        return mulInserSql.toString();
    }


    private BlockingQueue<User> initUserQueue(Integer size) {
        BlockingQueue<User> userQueue = new LinkedBlockingQueue<>();
        for (int i = 1; i <= size; i++) {
            User user = new User();
            user.setCity("shanghai" + i);
            user.setRegTime(new Date());
            user.setAge(i * 2 + 10);
            user.setEmail(i*100+"12334@qq.com");
            user.setNickName("nickName"+i);
            user.setId(i*1000);
            user.setUserName("ping" + i);
            user.setPassWord("15700" + i);
            userQueue.add(user);
        }
        return userQueue;
    }


}
