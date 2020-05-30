package com.sto.locker.codis;

import com.sto.locker.Locker;
import com.sto.utils.DataUtils;
import redis.clients.jedis.JedisCommands;

public abstract class AbstractCodisLocker<T extends JedisCommands> implements Locker {

	protected long timeout;

	protected abstract  T getRedis();

	protected abstract void returnRedis(T t);

	@Override
	public long tryLock(String key) {
		return tryLock(key, timeout);
	}

	@Override
	public long tryLock(String key, long lockTimeOut) {
		//得到锁后设置的过期时间，未得到锁返回0
		long expireTime = 0;
		T jedis = getRedis();
		try {
			expireTime = System.currentTimeMillis() + lockTimeOut + 1;
			if (jedis.setnx(key, String.valueOf(expireTime)) == 1) {
				//得到了锁返回
				return expireTime;
			} else {
				String curLockTimeStr = jedis.get(key);
				//判断是否过期
				if (DataUtils.isEmpty(curLockTimeStr)
						|| System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {
					expireTime = System.currentTimeMillis() + lockTimeOut + 1;

					curLockTimeStr = jedis.getSet(key, String.valueOf(expireTime));
					//仍然过期,则得到锁
					if (DataUtils.isEmpty(curLockTimeStr)
							|| System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {
						return expireTime;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			}
		} finally {
			returnRedis(jedis);
		}
	}

	@Override
	public void unLock(String key, long lockExpireTime) {
		if (System.currentTimeMillis() - lockExpireTime > 0) {
			return;
		}
		T jedis = getRedis();
		try {
			String curLockTimeStr = jedis.get(key);
			if (!DataUtils.isEmpty(curLockTimeStr) && Long.valueOf(curLockTimeStr) > System.currentTimeMillis()) {
				jedis.del(key);
			}
		} finally {
			returnRedis(jedis);
		}
	}

	@Override
	public Locker setTimeOut(long expireTime) {
		this.timeout = expireTime;
		return this;
	}
}
