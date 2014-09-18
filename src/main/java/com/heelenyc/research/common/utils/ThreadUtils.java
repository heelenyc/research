package com.heelenyc.research.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heelenyc.research.common.lrumap.LRUMap;

/**
 * @author yicheng
 * @since 2014年6月11日
 *
 */
public class ThreadUtils {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static final Log LOG = LogFactory.getLog(ThreadUtils.class);
    
    public static void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }
    
    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            LOG.error("sleep interrupted", e);
        }
    }
    
}