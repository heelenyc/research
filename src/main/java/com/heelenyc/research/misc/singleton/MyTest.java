package com.heelenyc.research.misc.singleton;

public class MyTest {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.heelenyc.research.misc.singleton.Singleton");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Singleton.getInstance().add(1, 3));
	}
}