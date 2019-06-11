/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: CacheMap
 * Author:   thinkpad
 * Date:     2019-06-11 23:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-06-11.
 */

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-06-11
 * @since 1.0.0
 */
public class CacheMap {
    //键值对集合
    private final static ConcurrentHashMap<String, Entity> map = new ConcurrentHashMap<>();
    //定时器线程池，用于清除过期缓存
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * 添加缓存
     * @param key
     * @param data
     */
    public  static void put(String key, Object data){
        Cache.put(key, data, 0);
    }

    /**
     * 添加缓存
     * @param key
     * @param data
     * @param expire    过期时间，单位：毫秒， 0表示无限长
     */
    public  static void put(String key, Object data, long expire){
        //清除原键值对
        Cache.remove(key);
        //设置过期时间
        if (expire > 0) {
            Future future = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    //过期后清除该键值对
//                    synchronized (Cache.class) {
                        map.remove(key);
//                    }
                }
            }, expire, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(data, future));
        }else map.put(key, new Entity(data, null));   //不设置过期时间
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return
     */
    public  static Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 读取缓存
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public  static <T> T get(String key, Class<T> tClass){
        return tClass.cast(Cache.get(key));
    }

    /**
     * 清除缓存
     * @param key
     * @return
     */
    public  static Object remove(String key){
        //清除原缓存数据
        Entity entity = map.remove(key);
        if (null == entity) {
            return null;
        }
        //清除原键值对定时器
        Future future = entity.getFuture();
        if (null != future) {
            future.cancel(true);
        }
        return entity.getValue();
    }
    /**
     * 查询当前缓存的键值对数量
     * @return
     */
    public  static int size() {
        return map.size();
    }


}
