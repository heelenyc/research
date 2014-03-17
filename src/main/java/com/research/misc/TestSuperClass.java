package com.research.misc;

import java.util.Date;

/**
 * @author yicheng
 * @since 2014年2月25日
 *
 */
public class TestSuperClass extends Date{

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TestSuperClass().test();
    }

    /**
     * 
     */
    private void test() {
        System.out.println(getClass().getName());
        System.out.println(getClass().getSuperclass().getName());
    }

}
