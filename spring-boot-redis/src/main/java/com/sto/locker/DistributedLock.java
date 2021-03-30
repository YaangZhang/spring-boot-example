
package com.sto.locker;

import com.sto.locker.exception.NotFetchLockException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class DistributedLock {


    private static final int RETRY_COUNT = 1;
    private static final long DELAY_TIME = 100;
    private static final long EXPIRE_TIME = 1000;
    private static Logger log = LoggerFactory.getLogger(DistributedLock.class);

    /**
     * 构造函数
     *
     * @param locker 锁实例
     */
    public DistributedLock(Locker locker, String namespace) {
        this.locker = locker;
        this.namespace = namespace;
    }


    /**
     * 锁实例
     */
    private Locker locker;

    public DistributedLock setLocker(Locker locker) {
        this.locker = locker;
        return this;
    }

    /**
     * 命名空间
     */
    private String namespace;

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * 获取锁编号
     *
     * @param id 关键词
     * @return 锁编号
     */
    private String getLockerId(String id) {
        return MessageFormat.format("locker:{0}:{1}", namespace, DigestUtils.md5Hex(id));
    }

    public <E> E execute(DistributedTask<E> task, String id) throws InterruptedException, NotFetchLockException {
        return execute(task, id, RETRY_COUNT, DELAY_TIME, EXPIRE_TIME);
    }

    public <E> E execute(DistributedTask<E> task, String id, Runnable runnable) throws InterruptedException, NotFetchLockException {
        return execute(task, id, RETRY_COUNT, DELAY_TIME, EXPIRE_TIME, runnable);
    }

    public <E> E execute(DistributedTask<E> task, String id, int retryCount) throws InterruptedException, NotFetchLockException {
        return execute(task, id, retryCount, DELAY_TIME, EXPIRE_TIME);
    }

    public <E> E execute(DistributedTask<E> task, String id, int retryCount, Runnable runnable) throws InterruptedException, NotFetchLockException {
        return execute(task, id, retryCount, DELAY_TIME, EXPIRE_TIME, runnable);
    }

    public <E> E execute(DistributedTask<E> task, String id, int retryCount, long expiredTime) throws InterruptedException, NotFetchLockException {
        return execute(task, id, retryCount, DELAY_TIME, expiredTime);
    }

    public <E> E execute(DistributedTask<E> task, String id, int retryCount, long expiredTime, Runnable runnable) throws InterruptedException, NotFetchLockException {
        return execute(task, id, retryCount, DELAY_TIME, expiredTime, runnable);
    }

    public <E> E execute(DistributedTask<E> task, String id, final int retryCount, long delayTime, long expiredTime) throws InterruptedException, NotFetchLockException {
        final String key = getLockerId(id);
        long getLock = 0;
        int cnt = retryCount;
        while (getLock == 0 && (retryCount == -1 || cnt-- > 0)) {
            getLock = locker.tryLock(key, expiredTime);
            if (getLock > 0) {
                try {
                    return task.run();
                } finally {
                    locker.unLock(key, getLock);
                }
            }
            Thread.sleep(delayTime);
        }

        log.warn("not fetch lock for id={} retryCount={} delayTime(ms)={}", id, retryCount, delayTime);
        return null;
    }

    public <E> E execute(DistributedTask<E> task, String id, final int retryCount, long delayTime, long expiredTime, Runnable runnable) throws InterruptedException, NotFetchLockException {
        final String key = getLockerId(id);
        long getLock = 0;
        int cnt = retryCount;
        while (getLock == 0 && (retryCount == -1 || cnt-- > 0)) {
            getLock = locker.tryLock(key, expiredTime);
            if (getLock > 0) {
                try {
                    return task.run();
                } catch (Exception ex) {
                    if (runnable != null) {
                        runnable.run();
                    }
                } finally {
                    locker.unLock(key, getLock);
                }
            }
            Thread.sleep(delayTime);
        }

        log.warn("not fetch lock for id={} retryCount={} delayTime(ms)={}", id, retryCount, delayTime);
        return null;
    }

    public long getLock(int expiredTime, final String key) {
        try {
            return locker.tryLock(key, expiredTime);
        } catch (Exception e) {
            log.info("try to get Lock Exception:" + e.getMessage());
        }
        return 0;
    }


    public <E> E execute2(DistributedTask<E> task, String id, final int retryCount, long delayTime, long expiredTime) throws InterruptedException, NotFetchLockException {
        final String key = getLockerId(id);
        boolean getLock = false;
        int cnt = retryCount;
        while (!getLock && (retryCount == -1 || cnt-- > 0)) {
            getLock = locker.lock(key,"11111", expiredTime);
            if (getLock) {
                try {
                    System.out.println("拿到鎖了");
                    return task.run();
                } finally {
                    System.out.println("ok");;
                }
            }
            Thread.sleep(delayTime);
        }

        log.warn("not fetch lock for id={} retryCount={} delayTime(ms)={}", id, retryCount, delayTime);
        return null;
    }
}