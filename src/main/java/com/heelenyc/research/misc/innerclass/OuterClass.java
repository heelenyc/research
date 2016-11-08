package com.heelenyc.research.misc.innerclass;

/**
 * @author yicheng
 * @since 2014年2月25日
 *
 */


public class OuterClass {
    public static String ss = "i am a static member";
    public String s = "i am a non-static member";
    
    public void outerFun(){
        OuterClass outer = new OuterClass();
        OuterClass.NonStaticInner inner = outer.new NonStaticInner();
    }
    
    class NonStaticInner{
        public void innerfun() {
            System.out.println(s);
            System.out.println(ss);
            OuterClass.this.outerFun();
        }
    }
    
    static class StaticInner{
        public void innerfun() {
            //System.out.println(s); // Cannot make a static reference to the non-static field s
            System.out.println(ss);
        }
    }
}
