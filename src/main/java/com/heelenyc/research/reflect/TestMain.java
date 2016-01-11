package com.heelenyc.research.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javassist.expr.NewArray;

/**
 * @author yicheng
 * @since 2014年10月13日
 * 
 */
public class TestMain {

    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // throws NoSuchMethodException, SecurityException,
        // ClassNotFoundException {
        // AddInface addObject = new AddImp();
        //
        // InvocationHandler handler = new MyInvocationHandler(addObject);
        // Class<?> cls = addObject.getClass();
        //
        // AddInface proxy =
        // (AddInface)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        // cls.getInterfaces(), handler);
        //
        // System.out.println(proxy.addlong(1.0E1, 2.0E1));
        Class<?> api = Class.forName("com.heelenyc.research.reflect.AddInface");
        System.out.println(api.getName());
        System.out.println(api.getSimpleName());
        for (Method m : api.getMethods()) {
            System.out.println(m);
            System.out.println(m.getName());
        }
        Method longadd = api.getMethod("addlong", long.class,long.class);
        System.out.println(longadd.invoke(new AddImp(), 2.0,3.0));

        // Class<?> api =
        // Class.forName("com.heelenyc.research.reflect.MyInterface");
        // // Method method = MyInterface.class.getMethod("get",
        // // int.class,int.class);
        // Method[] methods = api.getMethods();
        // for (Method m : methods) {
        // System.out.println(m.getName());
        // out(m.getGenericParameterTypes());//
        // [Ljava.lang.reflect.Type;@2f67d81
        // out(m.getParameterTypes());// [Ljava.lang.Class;@38b72ce1
        // out(m.getTypeParameters());//
        // [Ljava.lang.reflect.TypeVariable;@1e384de
        //
        //
        //
        // System.out.println(m.getGenericReturnType());//
        // java.util.List<java.lang.String>
        // System.out.println(m.getReturnType());// interface java.util.List
        //
        // //m.i
        // }
    }

    private static void out(Object[] objs) {
        for (Object object : objs) {
            System.out.println(object);
        }
    }
}
