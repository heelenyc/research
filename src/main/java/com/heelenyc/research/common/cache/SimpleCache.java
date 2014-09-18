package com.heelenyc.research.common.cache;

/**
 * 一个基于本地内存的简单缓存器
 * 
 * @param <T> type
 */
public abstract class SimpleCache<T> {
    private T cache;
    private long lastUpdateTime;
    private int expires;

    /**
     * constractor
     * 
     * @param expires expires in millisecond.
     */
    public SimpleCache(int expires) {
        this.expires = expires;
    }

    /**
     * get value from cache.
     * 
     * @return value
     */
    public T get() {
        if (cache == null || lastUpdateTime + expires <= System.currentTimeMillis()) {
            updateCache();
        }
        return cache;
    }

    /**
     * the value update method if cache expired.
     * 子类必须重写的方法
     * @return the new value
     */
    public abstract T update();
    
    /**
     * 更新缓存的接口
     */
    public void updateCache(){
    	cache = update();
    	lastUpdateTime = System.currentTimeMillis();
    }

}
