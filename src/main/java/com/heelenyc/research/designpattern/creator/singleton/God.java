package com.heelenyc.research.designpattern.creator.singleton;

/**
 * 全宇宙唯一的上帝
 * @author yicheng
 *
 */
public class God {
	private static God god;
	
	public static God getGod() {
		if (null == god) {
			synchronized (God.class) {
				if (null == god) {
					god = new God();
				}
			}
		}
		return god;
	}
	
	public void godSay() {
		System.out.println("主爱世人！");
	}
}
