package com.research.misc.TestPermOverflow;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author yicheng
 * @since 2014年3月4日
 * 
 */
public class TestPermOverflow {

    static class OOMOjbect {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer eh = new Enhancer();
            eh.setSuperclass(OOMOjbect.class);
            eh.setUseCache(false);
            eh.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
                    return arg3.invokeSuper(arg0, arg2);
                }

            });
            eh.create();
        }
    }

}