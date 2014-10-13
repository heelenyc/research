package com.heelenyc.research.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yicheng
 * @since 2014年10月13日
 * 
 */
public class TestMain {

    public static void main(String args[]){
        AddInface addObject = new AddImp();
        
        InvocationHandler handler = new MyInvocationHandler(addObject);
        Class<?> cls = addObject.getClass();
        
        AddInface proxy = (AddInface)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), cls.getInterfaces(), handler);
        
        System.out.println(proxy.add(1, 2));
    }
}
