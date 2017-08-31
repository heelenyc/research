package com.heelenyc.research.reflect.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

	private Object delegate; // 委托对象

	public Object getDelegate() {
		return delegate;
	}

	private DynamicProxy(Object delegate) {
		this.delegate = delegate;
	}

	public static Object bind(Object delegate) {
		InvocationHandler handler = new DynamicProxy(delegate);
		Class<?> cls = delegate.getClass();

		Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				cls.getInterfaces(), handler);
		return proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(delegate, args);
	}

}
