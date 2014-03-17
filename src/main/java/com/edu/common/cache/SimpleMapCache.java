package com.edu.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个基于本地内存的简单缓存器,map结构
 * 
 * @param <V> type of cached element
 * @param <K> type of the key for cached element
 */
public abstract class SimpleMapCache<K,V> {
    private Map<K, V> cache = new HashMap<K, V>();
    private Map<K, Long> lastUpdateTimeMap = new HashMap<K, Long>();
    private int expires;

    /**
     * constractor
     * 
     * @param expires expires in millisecond.
     */
    public SimpleMapCache(int expires) {
        this.expires = expires;
    }

    /**
     * get value from cache.
     * 
     * @param p param type
     * @return value
     */
    public V get(K p) {
        Long lastUpdateTime = lastUpdateTimeMap.get(p);
        if (lastUpdateTime == null) {
            lastUpdateTime = 0L;
        }

        if (lastUpdateTime + expires <= System.currentTimeMillis() || cache.get(p) == null) {
            reload(p);
        }
        return cache.get(p);
    }

    /**
     * the value update method if cache expired. 子类必须重写的方法
     * 
     * @param p param type
     * @return the new value
     */
    public abstract V update(K p);

    /**
     * 清空缓存
     */
    public void clear() {
        cache.clear();
    }

    /**
     * 清缓存
     * 
     * @param p param type
     */
    public void reload(K p) {
        synchronized (cache) {
            cache.put(p, update(p));
        }
        synchronized (lastUpdateTimeMap) {
            lastUpdateTimeMap.put(p, System.currentTimeMillis());
        }
    }

}
