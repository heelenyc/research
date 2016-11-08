package com.heelenyc.research.misc.orderedmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.immomo.mcf.util.LogUtils;

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
//        Map<String, Integer> map = null;
//        // map = new LinkedHashMap<>();
//        // fun(map);
//
//        map = new TreeMap<String, Integer>();
//        fun(map);
        
        Map<String, Integer> ipCounter = new HashMap<String, Integer>() ;
        ipCounter.put("1", 1);
        ipCounter.put("3", 3);
        ipCounter.put("2", 2);
        
        Integer j = ipCounter.get("3"); j++;
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(ipCounter .entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (int i = 0; i < 5; i++) {
            if (list.get(i) != null) {
                System.out.println(String.format("ip : %s  psec-num : %d",  list.get(i).getKey(), list.get(i).getValue()));
            }
        }
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
