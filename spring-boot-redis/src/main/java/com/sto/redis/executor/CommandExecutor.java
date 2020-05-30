package com.sto.redis.executor;

import redis.clients.jedis.Jedis;

public abstract class CommandExecutor<T> {

    /**
     * abstract execute logic
     *
     * @param connection redis connection
     * @return execute result
     */
    public abstract T execute(Jedis connection);
}