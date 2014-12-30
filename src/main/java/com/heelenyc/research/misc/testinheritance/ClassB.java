package com.heelenyc.research.misc.testinheritance;

/**
 * @author yicheng
 * @since 2014年12月19日
 * 
 */
public class ClassB extends ClassA implements InterfaceA {

    @Override
    public void funA() {

    }
    
    public static void foo() {
        System.out.println("hello world!");
    }
    
    public static void main(String[] args) {
        ClassB b = null;
        b.foo();
    }

}
