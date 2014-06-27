package com.research.misc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author yicheng
 * @since 2014年2月25日
 *
 */
public class TestString {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        String s = "a";
//        s = s + "b";
//        s.intern();
//        System.out.println(s == "ab");
//        
//        StringBuilder sbd = new StringBuilder();  // 非线程安全的。
//        StringBuffer sbfBuffer = new StringBuffer(); // 线程安全的。
        
//        String str = "zhangsan,lisi , sdfsdfn, sdfsdf ";
//        String[] array = str.split(",|:|;");
//        for (int i = 0; i < array.length; i++) {
//            array[i] = array[i].trim();
//        }
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
        
//        Set<String> set = new HashSet<String>();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");
//        set.add("5");
//        
//        Object[] array = set.toArray();
//        
//        for (int i = 0; i < array.length; i++) {
//            System.out.println((String)array[i]);
//        }
//        Set<Object> top5 = new HashSet<Object>();
//        for (int i = 0; i < 5; i++) {
//            top5.add(array[i]);
//        }
//        String monitorKeySetStr = StringUtils.join(top5, ',');
//        System.out.println(monitorKeySetStr);
        
//        Calendar nowDate = Calendar.getInstance(); 
//        nowDate.set(Calendar.HOUR_OF_DAY, 0);  
//        System.out.println(nowDate.getTime());
//        System.out.println(nowDate.get(Calendar.AM));
//        System.out.println(nowDate.get(Calendar.AM_PM));
//        System.out.println(nowDate.get(Calendar.HOUR));
//        
//        nowDate.set(Calendar.HOUR_OF_DAY, 12);  
//        System.out.println(nowDate.getTime());
//        System.out.println(nowDate.get(Calendar.AM));
//        System.out.println(nowDate.get(Calendar.AM_PM));
//        System.out.println(nowDate.get(Calendar.HOUR));
        
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("platform", "platform");
        info.put("operator", "operator");
        info.put("event", "event");
        System.out.println(info.toString());
    }

}
