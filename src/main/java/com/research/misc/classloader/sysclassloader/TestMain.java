package com.research.misc.classloader.sysclassloader;

/**
 * @author yicheng
 * @since 2014年3月24日
 *
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader().getParent());
    }

}
