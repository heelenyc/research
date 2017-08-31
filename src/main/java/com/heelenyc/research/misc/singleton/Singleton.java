package com.heelenyc.research.misc.singleton;

public class Singleton {
	
	static {
		System.out.println("static init!");
	}

	private Singleton() {
		System.out.println("Singleton constructing!");
	}
	
	private static class SingletonHolder {
		public static Singleton inc = new Singleton(); // jvm 加载的时候new 单例实例，从jvm层面上避免多线程问题，因为jvm只允许一个线程加载类（包括执行类的初始化）
	}
	
	/** 
	 * getInstance 只有真正调用这个方法的时候，才会通过加载SingletonHolder来是构建singleton单例，达到延迟加载的目的
	 * @return
	 */
	public static Singleton getInstance(){
		return SingletonHolder.inc;
	}
	
	public int add(int op1, int op2){
		return op1 + op2;
	}
	
}
