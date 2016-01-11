package com.heelenyc.research.misc.testsize;

/**
 * @author yicheng
 * @since 2015年7月21日
 *
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println(SizeCounter.getObjectSize(new A()));
    }
}

class A{
    
    private int a;
    private Long bLong;
    private char c;
}
