package com.heelenyc.research.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.immomo.mcf.util.LogUtils;

/**
 * 一个基于本地内存的简单缓存器,map结构
 * 
 * @param <V>
 *            type of cached element
 * @param <K>
 *            type of the key for cached element
 */
public abstract class SimpleMapCache<K, V> {
    private Logger logger = Logger.getLogger(SimpleMapCache.class);
    
    private Map<K, V> cache = new ConcurrentHashMap<K, V>();
    private Map<K, Long> lastUpdateTimeMap = new ConcurrentHashMap<K, Long>();
    private int expiresInMS;
    private ScheduledExecutorService scheduledExecutors = Executors.newScheduledThreadPool(1);

    /**
     * constractor
     * 
     * @param expiresInMS
     *            expires in millisecond.
     */
    public SimpleMapCache(int expiresInMS, int clearDelayInMs) {
        this.expiresInMS = expiresInMS;
        if (clearDelayInMs < expiresInMS) {
            clearDelayInMs = expiresInMS;
        }
        scheduledExecutors.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                // System.out.println("clear");
                clear();
            }
        }, clearDelayInMs, clearDelayInMs, TimeUnit.MILLISECONDS);
    }

    /**
     * get value from cache.
     * 
     * @param p
     *            param type
     * @return value
     */
    protected V get(K p) {
        Long lastUpdateTime = lastUpdateTimeMap.get(p);
        if (lastUpdateTime == null) {
            lastUpdateTime = 0L;
        }

        if (lastUpdateTime + expiresInMS <= System.currentTimeMillis() || cache.keySet().contains(p) == false) {
            reload(p);
        }
        return cache.get(p);
    }

    /**
     * the value update method if cache expired. 子类必须重写的方法
     * 
     * @param p
     *            param type
     * @return the new value
     */
    public abstract V update(K p);

    /**
     * 清空缓存  只会清理过期的key
     */
    public void clear() {
        List<K> toRemoved = new ArrayList<K>();
        long now = System.currentTimeMillis();
        for( Entry<K, Long> entry : lastUpdateTimeMap.entrySet()){
            if (now - entry.getValue() > expiresInMS) {
                toRemoved.add(entry.getKey());
            }
        }
        LogUtils.info(logger, "SimpleMapCache to clear : toremoved/total = {0}/{1}", toRemoved.size(),lastUpdateTimeMap.size());
        for (K k : toRemoved) {
            cache.remove(k);
            lastUpdateTimeMap.remove(k);
        }
//        cache.clear();
//        lastUpdateTimeMap.clear();
    }

    /**
     * 重新载入p对应的value
     * 
     * @param p
     *            param type
     */
    public void reload(K p) {
        cache.put(p, update(p));
        lastUpdateTimeMap.put(p, System.currentTimeMillis());
    }

    public void remove(K p) {
        cache.remove(p);
        lastUpdateTimeMap.remove(p);
    }
}
