package com.sto.redis;

import com.sto.redis.executor.CommandExecutor;
import com.sto.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author zhy
 * @create 2019-12-02-10:36
 */
@Service
public class RedisService2 {
    @Autowired
    @Qualifier("codisFactorySlave")
    private CodisFactory codisFactorySlave;

    @Autowired
    @Qualifier("codisFactory")
    private CodisFactory codisFactory;

    Logger logger = LoggerFactory.getLogger(RedisService2.class);
    public boolean setSubjectInfo(final String subjectInfo) {

        /*
         * data init
         */
        String val = null;
        boolean action = true;

        /*
         * execute
         */
        try {

            /*
             * validation
             */
            if (DataUtils.isEmpty(subjectInfo)) {
                throw new Exception("parameter -> subjectInfo can not be null or empty");
            }

            /*
             * execute
             */
            action = codisFactory.execute(new CommandExecutor<Boolean>() {

                @Override
                public Boolean execute(Jedis connection) {

                    /*
                     * get redis key
                     */
                    String key = "abcd";

                    /*
                     * save or delete subjectInfo into redis service
                     */
                        connection.set(key, subjectInfo);

                    /*
                     * return
                     */
                    return true;
                }

            }, logger);

        } catch (Exception ex) {
            action = false;
//            LoggerUtils.LOGGER_REDIS_SERVICE.error("insert or update subjectInfo into redis service met error, subjectInfo:{}, error:{}", val, ex);
        } finally {
//            LoggerUtils.LOGGER_REDIS_SERVICE.info("insert or update subjectInfo into redis service, subjectInfo:{}, action:{}", val, action);
        }

        /*
         * return
         */
        return action;
    }

    public String getSubjectInfo(String id) {

        /*
         * data init
         */
        String val = null;
        boolean action = true;

        /*
         * execute
         */
        try {
            val = codisFactorySlave.execute(new CommandExecutor<String>() {
                @Override
                public String execute(Jedis connection) {

                    String key = "abcd";

                    return connection.get(key);
                }
            }, logger);

        } catch (Exception ex) {
            action = false;
//            LoggerUtils.LOGGER_REDIS_SERVICE.error("get subjectInfo from redis service met error, subjectId:{}, error:{}", id, ex);
        } finally {
//            LoggerUtils.LOGGER_REDIS_SERVICE.info("get subjectInfo from redis service, subjectId:{}, subjectInfo:{}, action:{}", id, val, action);
        }

        /*
         * return
         */
        return val;
    }


}
