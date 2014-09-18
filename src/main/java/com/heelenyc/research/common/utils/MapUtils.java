package com.heelenyc.research.common.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yicheng
 * @since 2014年1月26日
 * 
 */
public class MapUtils {

    /**
     * 按照map的value排序，value必须是实现了comparable接口的封装类。
     * @param map
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map,final boolean isAsc) {
        
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if (isAsc) {                    
                    return (o1.getValue()).compareTo(o2.getValue());
                }else {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<Object, Integer> map = new HashMap<Object, Integer>();
        map.put("zhangsan", 20);
        map.put("lisi",2);
        map.put("wangwu", 78);
        map.put(123123, 100);
        
        System.out.println(map);
        System.out.println(sortByValue(map,false));
    }

}
