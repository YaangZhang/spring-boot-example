package com.sto.redis;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.sto.redis.entities.ConnectConfig;
import com.sto.redis.entities.ConnectionInfo;
import com.sto.redis.executor.CommandExecutor;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CodisFactory implements Closeable {
    private final ConnectConfig connectConfig;
    private final JedisPoolConfig poolConfig;
    private final AtomicInteger nextIdx = new AtomicInteger(-1);
    private volatile ImmutableList<ConnectionInfo> immutableList = ImmutableList.of();

    public CodisFactory(JedisPoolConfig poolConfig, ConnectConfig connectConfig) {
        this.poolConfig = poolConfig;
        this.connectConfig = connectConfig;
    }

    public Jedis getJedisResource() {
        Jedis jedis = null;
        JedisPool pool = this.getJedisPoolResource();
        if (pool != null) {
            jedis = pool.getResource();
        }

        return jedis;
    }

    private JedisPool getJedisPoolResource() {
        ImmutableList<ConnectionInfo> pools = this.immutableList;
        ImmutableList.Builder<ConnectionInfo> builder = ImmutableList.builder();
        Map<String, ConnectionInfo> connectObjectMapping = Maps.newHashMapWithExpectedSize(pools.size());
        UnmodifiableIterator var9 = pools.iterator();

        ConnectionInfo info;
        while(var9.hasNext()) {
            info = (ConnectionInfo)var9.next();
            connectObjectMapping.put(info.getAddress(), info);
        }

        String[] var13 = this.connectConfig.getAddress().split(",");
        int next = var13.length;

        for(int var11 = 0; var11 < next; ++var11) {
            String address = var13[var11];
            ConnectionInfo connectObject = (ConnectionInfo)connectObjectMapping.remove(address);
            String[] hostAndPort;
            if (connectObject == null && (hostAndPort = address.split(":")).length == 2) {
                String host = hostAndPort[0];
                int port = Integer.parseInt(hostAndPort[1]);
                JedisPool pool = new JedisPool(this.poolConfig, host, port, this.connectConfig.getConnectionTimeout(), this.connectConfig.getPassWord(), this.connectConfig.getDatabase());
                connectObject = new ConnectionInfo(host, port, pool);
            }

            if (connectObject != null) {
                builder.add(connectObject);
            }
        }

        this.immutableList = builder.build();
        Iterator var14 = connectObjectMapping.values().iterator();

        while(var14.hasNext()) {
            info = (ConnectionInfo)var14.next();
            info.getPool().close();
        }

        int current;
        do {
            current = this.nextIdx.get();
            next = current >= pools.size() - 1 ? 0 : current + 1;
        } while(!this.nextIdx.compareAndSet(current, next));

        return ((ConnectionInfo)this.immutableList.get(next)).getPool();
    }

    public <T> T execute(CommandExecutor<T> executor, Logger logger) throws Exception {
        T result = null;
        if (logger == null) {
            throw new Exception("parameter -> logger can not be null or empty");
        } else if (executor == null) {
            throw new Exception("parameter -> executor can not be null or empty");
        } else {
            try {
                Jedis jedis = this.getJedisResource();
                Throwable var5 = null;

                try {
                    if (jedis != null) {
                        result = executor.execute(jedis);
                    }
                } catch (Throwable var15) {
                    var5 = var15;
                    throw var15;
                } finally {
                    if (jedis != null) {
                        if (var5 != null) {
                            try {
                                jedis.close();
                            } catch (Throwable var14) {
                                var5.addSuppressed(var14);
                            }
                        } else {
                            jedis.close();
                        }
                    }

                }
            } catch (Exception var17) {
                logger.error("execute redis command logic met error, error:{}", var17);
            }

            return result;
        }
    }

    public void close() {
        List<ConnectionInfo> pools = this.immutableList;
        this.immutableList = ImmutableList.of();
        Iterator var2 = pools.iterator();

        while(var2.hasNext()) {
            ConnectionInfo object = (ConnectionInfo)var2.next();
            object.getPool().close();
        }

    }

    public void returnResource(Jedis connection) {
        if (connection != null) {
            connection.close();
        }

    }
}
