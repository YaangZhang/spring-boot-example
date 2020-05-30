package com.sto.redis.entities;

import redis.clients.jedis.JedisPool;

import java.text.MessageFormat;

public class ConnectionInfo {

    /**
     * host
     */
    private final String host;

    public String getHost() {
        return host;
    }

    /**
     * ort
     */
    private final int port;

    public int getPort() {
        return port;
    }

    /**
     * pool
     */
    private final JedisPool pool;

    public JedisPool getPool() {
        return pool;
    }

    /**
     * constructor
     *
     * @param host host
     * @param pool pool
     */
    public ConnectionInfo(String host, int port, JedisPool pool) {
        this.host = host;
        this.port = port;
        this.pool = pool;
    }

    /**
     * get address
     *
     * @return address
     */
    public String getAddress() {
        return MessageFormat.format("{0}:{1}", this.getHost(), String.valueOf(this.getPort()));
    }
}