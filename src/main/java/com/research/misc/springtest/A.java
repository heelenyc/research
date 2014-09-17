package com.research.misc.springtest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @author yicheng
 * @since 2014年9月10日
 * 
 */

@Component
public class A {

    @Resource
    private B b;

    private Map<String, B> map = new HashMap<String, B>();

    private A() {
        // System.out.println("put b into map");
        // map.put("B", b);
        System.out.println("in A's constructor!");
    }

    @PostConstruct
    private void init() {
        System.out.println("put b into map");
        map.put("B", b);
    }

    public void fun() {
        B b = map.get("B");
        System.out.println(b.getName());
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        System.out.println("property b is set!");
    }
}
