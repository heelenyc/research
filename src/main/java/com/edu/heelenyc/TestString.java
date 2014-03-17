package com.edu.heelenyc;

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
        String s = "a";
        s = s + "b";
        s.intern();
        System.out.println(s == "ab");
        
        StringBuilder sbd = new StringBuilder();  // 非线程安全的。
        StringBuffer sbfBuffer = new StringBuffer(); // 线程安全的。
    }

}
