package com.research.misc.orderedmap;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yicheng
 * @since 2014年3月5日
 * 
 */
public class TestTreeMap {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> map = null;
        // map = new LinkedHashMap<>();
        // fun(map);

        map = new TreeMap<String, Integer>();
        fun(map);
    }

    private static void fun(Map<String, Integer> map) {
        System.out.println(map.getClass().getSimpleName());
        map.put("zhangsan", 1);
        map.put("lisi", 2);map.put("lisi", 8);
        map.put("wangwu", 3);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

}
