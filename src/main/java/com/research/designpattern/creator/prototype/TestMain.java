package com.research.designpattern.creator.prototype;

public class TestMain {

	public static void main(String[] args) {
		PrototypeString s1 = new PrototypeString("张三");
		PrototypeString s2 = s1.clone();
		
		s1.say();
		s2.say();
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
	}

}
