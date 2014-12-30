package com.heelenyc.research.misc.hook;

/**
 * @author yicheng
 * @since 2014年11月4日
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// add a jvm shutdown hook test
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				System.out.println("i am shutdowning !");
			}
		});
	}

}
