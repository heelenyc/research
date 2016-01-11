package com.heelenyc.research.misc.ifelse;

/**
 * @author yicheng
 * @since 2015年7月16日
 * 
 */
public class TestMain {

    private static boolean fun1(String test) {
        System.out.println("begin fun1");
        return test.equals("test");
    }

    private static boolean fun2(String test) {
        System.out.println("begin fun2");
        return test.equals("hello world");
    }

    public static void main(String args[]) {
        if (fun1("") || fun2("sdfs")) {

        }

        if (fun1("") || fun2("sdfs")) {

        }
    }
}
