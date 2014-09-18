package com.heelenyc.research.designpattern.creator.singleton;

public class TestMain {

	public static void main(String[] args) {
		God god1 = God.getGod();
		God god2 = God.getGod();
		
		System.out.println(god1);
		System.out.println(god1 == god2);
	}

}
