package com.heelenyc.research.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author yicheng
 * @since 2014年10月13日
 * 
 */
public class TestMain {

    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // AddInface addObject = new AddImp();
        //
        // InvocationHandler handler = new MyInvocationHandler(addObject);
        // Class<?> cls = addObject.getClass();
        //
        // AddInface proxy = (AddInface)
        // Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        // cls.getInterfaces(), handler);
        //
        // System.out.println(proxy.addlong(1, 2));

        // Class<?> api =
        // Class.forName("com.heelenyc.research.reflect.AddInface");
        // System.out.println(api.getName());
        // System.out.println(api.getSimpleName());
        // for (Method m : api.getMethods()) {
        // System.out.println(m);
        // System.out.println(m.getName());
        // }
        // Method longadd = api.getMethod("addlong", long.class, long.class);
        // System.out.println(longadd.invoke(new AddImp(), 2, 3));

        // Class<?> api =
        // Class.forName("com.heelenyc.research.reflect.MyInterface");
        // Method[] methods = api.getMethods();
        // for (Method m : methods) {
        // System.out.println(m.getName());
        // out(m.getGenericParameterTypes());//
        // out(m.getParameterTypes());// [Ljava.lang.Class;@38b72ce1
        // out(m.getTypeParameters());//
        //
        // System.out.println(m.getGenericReturnType());//
        // System.out.println(m.getReturnType());// interface java.util.List
        //
        // }

        Class<?> api = Class.forName("com.heelenyc.research.reflect.AddImp");
        try {
            AddImp obj = (AddImp) api.newInstance();
            System.out.println(obj.add(1, 2));
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private static void out(Object[] objs) {
        for (Object object : objs) {
            System.out.println(object);
        }
    }
}
