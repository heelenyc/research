package com.edu.heelenyc.innerclass;

import com.edu.heelenyc.innerclass.OuterClass.NonStaticInner;

/**
 * @author yicheng
 * @since 2014年2月25日
 *
 */
public class TestMain {

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        SuperOfAnonimous anonimousInstanc = new SuperOfAnonimous() {
            
            @Override
            protected void fun() {
                System.out.println("i am a anonimous instance");
            }
        };
        anonimousInstanc.fun();
        
        NonStaticInner nonStaticInner = outer.new NonStaticInner();
        nonStaticInner.innerfun();
        OuterClass.StaticInner staticInner = new OuterClass.StaticInner();
        staticInner.innerfun();
    }
}
