package com.sto.locker.codis;


import com.sto.redis.CodisFactory;
import redis.clients.jedis.Jedis;

public class CodisLocker extends AbstractCodisLocker<Jedis> {

    /**
     * Codis Factory
     */
    private final CodisFactory codisFactory;

    /**
     * constructor
     *
     * @param codisFactory codis factory
     */
    public CodisLocker(CodisFactory codisFactory) {
        this.codisFactory = codisFactory;
    }

    @Override
    protected Jedis getRedis() {
        return this.codisFactory.getJedisResource();
    }

    @Override
    protected void returnRedis(Jedis jedis) {
        this.codisFactory.returnResource(jedis);
    }
}