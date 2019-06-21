package com.sto.modle.service;

import com.sto.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 多线程插入大量数据，批量插入速度达4000条/s.
 */
@Service
public class MultiInsert {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 操作数据库 插入数据
     * @param userQueue 数据源
     * @param sharding  操作类型（单条/批量）
     * @return
     */
    public long processQueue(BlockingQueue<User> userQueue, boolean sharding) {
        int mulSize = 200;
        long beginTime = System.currentTimeMillis();
        try {
            if (sharding) {
                while (!userQueue.isEmpty()) {
                    User user = userQueue.take();//阻塞类型 如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。
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

    /**
     * 拼接参数  每组8个字段
     * @param userList
     * @param mulSize
     * @return
     */
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

    /**
     * 拼接SQL 每次 insert mulSize 条
     * @param mulSize
     * @return
     */
    private String genMulFixSqlStr(int mulSize) {
        StringBuffer mulInserSql = new StringBuffer();
        mulInserSql.append("INSERT INTO `user` (`id`, `user_name`, `pass_word`, `age`, `city`, `email`, `nick_name`, `reg_time`) VALUES (?,?,?,?,?,?,?,?)");
        for (int i = 1; i < mulSize; i++) {
            mulInserSql.append(",(?,?,?,?,?,?,?,?)");
        }
        return mulInserSql.toString();
    }

    /**
     * 初始化数据 根据传入的数字创建对应量的数据源
     * @param size
     * @return
     */
    public BlockingQueue<User> initUserQueue(Integer size) {
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
