package com.heelenyc.research.misc.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yicheng
 * @since 2013年12月16日
 * 
 */
public class TestAtomicInteger {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        int i1 = ai.get();
        v(i1);
        int i2 = ai.getAndSet(5);
        v(i2);
        int i3 = ai.get();
        v(i3);
        int i4 = ai.getAndIncrement();
        v(i4);
        v(ai.get());
        
        String test = "test";
        test.intern();
        
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        
        

    }

    static void v(int i) {
        System.out.println("i : " + i);
    }
}
