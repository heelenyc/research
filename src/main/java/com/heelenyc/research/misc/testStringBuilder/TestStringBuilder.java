package com.heelenyc.research.misc.testStringBuilder;

public class TestStringBuilder {

	public static void main(String[] args) {
		String str = "start";
		for (int i = 0; i < 10; i++) {
			str = str + "hello";
		}
		System.out.println(str);
	}

}
