package com.heelenyc.research.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yicheng
 * @since 2014年10月13日
 *
 */
public class MyInvocationHandler  implements InvocationHandler{

    private Object object;
    
    MyInvocationHandler(Object imlObject){
        this.object = imlObject;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("calling in MyInvocationHandler " + method);  
        return method.invoke(object, args);  
    }

}
