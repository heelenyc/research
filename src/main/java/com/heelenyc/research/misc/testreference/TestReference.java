package com.heelenyc.research.misc.testreference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yicheng
 * @since 2015年1月7日
 * 
 */
public class TestReference {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Integer jInteger = 1;
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "2");

        A a = new A(0, jInteger, map);
        System.out.println(a.toString());
        map.put("1", "3");
        jInteger = 2;
        System.out.println(a.toString());
    }

    public static class A {
        int m_int;
        Integer m_intInteger;
        Map<String, String> m_map;

        public A(int i, Integer j, Map<String, String> map) {
            this.m_int = i;
            this.m_intInteger = j;
            this.m_map = map;

        }

        @Override
        public String toString() {
            return "A [m_int=" + m_int + ", m_intInteger=" + m_intInteger + ", m_map=" + m_map + "]";
        }

    }
}
