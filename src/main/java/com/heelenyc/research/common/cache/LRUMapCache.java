package com.heelenyc.research.common.cache;

import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import com.immomo.mcf.util.LogUtils;

/**
 * 不保证一致性，但是保证最终一致性，由keysize控制缓存大小，更精准一点，避免oom
 * @author yicheng
 * @since 2015年2月28日
 * 
 * @param <K>
 * @param <V>
 */
public abstract class LRUMapCache<K, V> {
    private Logger logger = LogUtils.createDatetimeLogger("LRUMapCache", "worker");

    private ConcurrentLinkedHashMap<K, V> cache;
    private ConcurrentHashMap<K, Long> lastUpdateDatetimestamp = new ConcurrentHashMap<K, Long>();
    private int expiresInMs;
    private int keyMaxSize;

    private AtomicLong totalCounter = new AtomicLong(0);
    private AtomicLong missCounter = new AtomicLong(0);

    private ScheduledExecutorService scheduledExecutors = Executors.newScheduledThreadPool(1);

    /**
     * constractor
     * 
     * @param expiresInMs
     *            expires in millisecond.
     */
    public LRUMapCache(int expiresInMs, int keyMaxSize) {
        this.expiresInMs = expiresInMs;
        this.keyMaxSize = keyMaxSize;

        this.cache = new ConcurrentLinkedHashMap.Builder<K, V>().maximumWeightedCapacity(this.keyMaxSize).listener(new EvictionListener<K, V>() {

            @Override
            public void onEviction(K key, V value) {
                // LogUtils.info(logger, "remove : key = {0}  value = {1}", key, value);
                lastUpdateDatetimestamp.remove(key);
            }
            
            
        }).build();

        // 每分钟打印状态
        scheduledExecutors.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                stat();
            }

        }, 5, 5, TimeUnit.SECONDS);
    }

    private void stat() {
        LogUtils.info(logger, "total/miss: {0} / {1} ; size: {2} / {3} ", totalCounter.getAndSet(0l), missCounter.getAndSet(0l), cache.size(), lastUpdateDatetimestamp.size());
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
     * get value from cache. 两种情况需要load，一是没有这个key，二是过期了
     * 
     * @param p
     *            param type
     * @return value
     */
    public V get(K p) {
        totalCounter.addAndGet(1l);
        if (p == null) {
            return null;
        }
        Long lastUpdatetime = lastUpdateDatetimestamp.get(p);
        if (!cache.containsKey(p) || lastUpdatetime == null || System.currentTimeMillis() - lastUpdatetime >= expiresInMs) {
            load(p);
            missCounter.addAndGet(1l);
        }
        return cache.get(p);
    }

    public synchronized V remove(K p) {
        lastUpdateDatetimestamp.remove(p);
        return cache.remove(p);
    }

    public int size() {
        return cache.size();
    }

    public Set<Entry<K, V>> entrySet() {
        return cache.entrySet();
    }

    public V load(K p) {
        V ret = null;
        if (p != null) {
            ret = update(p);
            cache.put(p, ret);
            lastUpdateDatetimestamp.put(p, System.currentTimeMillis());
        }
        return ret;
    }
    
//    public static void main(String[] args) throws InterruptedException {
//        LRUMapCache<String, String> map = new LRUMapCache<String, String>(10000,10) {
//
//            @Override
//            public String update(String p) {
//                return p;
//            }
//        };
//        
//        map.get("1");
//        map.get("2");
//        map.get("3");
//        map.get("4");
//        map.get("5");
//        
//        //map.remove("3");
//        
//        System.out.println(map.get("1"));
//        System.out.println(map.get("3"));
//        
//        TimeUnit.SECONDS.sleep(10);
//        
//        System.out.println(map.get("1"));
//        System.out.println(map.get("3"));
//    }

}
