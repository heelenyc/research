package com.research.common.lrumap;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.research.common.utils.ThreadUtils;

/**
 * 可以自动进行过期删除的LRUMap。
 */
public class LRUMap<K, V> {
    public static final Log LOG = LogFactory.getLog(LRUMap.class);

    private static final long YEAR_SECOND = 365 * 24 * 60 * 60;
    private static final long DELAY_IN_SECONDS = 10;

    private ConcurrentMap<K, V> map;
    private ConcurrentMap<K, Long[]> expireMap;
    private int capacity;

    private ScheduledExecutorService expireScanner = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    });

    public LRUMap(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentLinkedHashMap.Builder<K, V>().maximumWeightedCapacity(capacity).build();
        this.expireMap = new ConcurrentLinkedHashMap.Builder<K, Long[]>().maximumWeightedCapacity(capacity).build();
        expireScanner.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                int count = 0;
                for (K k : map.keySet()) {
                    get(k); // get操作中将检查过期。

                    if (++count % 1000 == 0) {
                        ThreadUtils.sleep(100);
                    }
                }
            }
        }, DELAY_IN_SECONDS, DELAY_IN_SECONDS, TimeUnit.SECONDS);

        // Shutdown.
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                try {
                    expireScanner.shutdown();
                    expireScanner.awaitTermination(1000, TimeUnit.MILLISECONDS);
                    expireScanner.shutdownNow();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public V get(K k) {
        Long[] expire = expireMap.get(k);

        if (expire != null && expire.length == 2) {
            // If expired: expireSecond * 1000 < (now - createTime)
            if (expire[1] * 1000 < (now() - expire[0])) {
                remove(k);
                LOG.info("Deleted key: " + k);
            }

        } else {
            remove(k);
        }

        return map.get(k);
    }

    public int size() {
        return this.map.size();
    }

    public void put(K k, V v, long expireSecond) {
        Long[] expire = new Long[] { now(), expireSecond };
        this.map.put(k, v);
        this.expireMap.put(k, expire);
    }

    public void put(K k, V v) {
        put(k, v, YEAR_SECOND);
    }

    public V remove(K k) {
        this.expireMap.remove(k);
        return this.map.remove(k);
    }

    public void clear() {
        this.map.clear();
        this.expireMap.clear();
    }

    public boolean containsKey(K k) {
        return get(k) != null;
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString() {
        return map.toString();
    }

}

