package com.research.misc;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author yicheng
 * @since 2013年11月26日
 * 
 */
abstract class A{
    protected abstract void fun() ;
}
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
//        A a = new A(){
//
//            @Override
//            final protected void fun() {
//                System.out.println(this.getClass().getSimpleName());
//            }
//            
//        };
//        a.fun();
        // Jedis conn = null;
        // try {
        // try {
        // conn = new Jedis("127.0.0.1", 6379);
        // conn.info();
        // } catch (Exception e) {
        // System.out.println(e.getCause().getMessage());
        // conn.info();
        // }
        // } catch (Exception e) {
        // System.out.println(e);
        // }

        // Number num = new Float(12.3f);
        //
        // System.out.println(num instanceof Float);
        // System.out.println(num instanceof Number);
        //
        // Exception exception = new Exception("hello");
        // System.out.println(exception.getMessage());

        // Person person = new Person("zhansan");
        // System.out.println(person);
        // process(person);
        // System.out.println(person);
        //
        // Integer a = 1000;
        // System.out.println(a);
        // process(a);
        // System.out.println(a);
        //
        // String str = "111";
        // System.out.println(str);
        // process(str);
        // System.out.println(str);
        //
        // int[] arr = { 1, 2 };
        // System.out.println(arr[0] + "," + arr[1]);
        // process(arr[0],arr[1]);
        // System.out.println(arr[0] + "," + arr[1]);

        // Set<String> set = null;
        // for (String string : set) {
        // System.out.println(string);
        // }

//        Map<Object, Integer> map = new ConcurrentHashMap<Object, Integer>();
//        map.put("zhangsan", 20);
//        map.put("lisi", 2);
//        map.put("wangwu", 78);
//        map.put(123123, 100);
//        //Hashtable<K, V>
//        map = sortByValue(map, true);
//
//        System.out.println(map.entrySet());
//        for (Entry<Object, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }
//
//        List<Integer> list = new ArrayList<Integer>();
//        Iterator<Integer> i = list.iterator();
//        
//              
//        Set<Integer> sets = new HashSet<Integer>(list);
//        
//        //ConcurrentHashMap<Object, Integet>
//        System.out.println(Double.isNaN(0d/0 ));
//        fun(new String[]{"hello,","world"});
        
//        String masterString = "edsn / sdfsdf";
//        String sub = "edsn".split("\\+|\\-|\\*|\\/")[0];
//        System.out.println(sub);
        
//        String str = "2324.0";
//        System.out.println(str.substring(0,str.indexOf(".")));
        
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        cal.setTimeInMillis(new Date().getTime() - 1000 * 60 * 60 * 24);
        System.out.println(cal.getTime());
    }
    
    
    public static void fun(String... a){
        System.out.println(a.toString());
    }
    public static <K,V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map,final boolean isAsc){
        List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<K, V>>() {
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                if (isAsc) {
                    return o1.getValue().compareTo(o2.getValue());
                }else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
            
        });
        
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    // private static void process(Person person) {
    // // person.setName("lisi");
    // person = new Person("lisi");
    // }
    //
    // private static void process(Integer a) {
    // a++;
    // }
    //
    // private static void process(String a) {
    // a = "222";
    // }
    //
    // private static void process(int[] a) {
    // int tmp = a[0];
    // a[0] = a[1];
    // a[1] = tmp;
    // }
    //
    // private static void process(int a, int b) {
    // int tmp = a;
    // a = b;
    // b = tmp;
    // }

    @SuppressWarnings("unchecked")
    public <T> T getTest(){
        return (T)new Object();
    }
    
    public Map<String, String> getMap() {
        return getTest();
    }
}

// class Person {
// private String name;
//
// /**
// * @param string
// */
// public Person(String string) {
// this.name = string;
// }
//
// public String getName() {
// return name;
// }
//
// public void setName(String name) {
// this.name = name;
// }
//
// public String toString() {
// return "person [name=" + name + "]";
// }
//
// }