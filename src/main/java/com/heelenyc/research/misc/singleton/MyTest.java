package com.heelenyc.research.misc.singleton;

/**
 * @author yicheng
 * @since 2015年8月13日
 * 
 */
class Singleton {
    public static Singleton singleton = new Singleton();
    public static int a;
    public static int b = 0;

    private Singleton() {
        super();
        a++;
        b++;
    }

    public static Singleton GetInstence() {
        return singleton;
    }
}

public class MyTest {
    /** * @param args */
    public static void main(String[] args) {
        Singleton mysingleton = Singleton.GetInstence();
        System.out.println(mysingleton.a);
        System.out.println(mysingleton.b);
    }
}
