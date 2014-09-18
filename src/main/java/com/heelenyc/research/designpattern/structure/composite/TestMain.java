package com.heelenyc.research.designpattern.structure.composite;

import com.heelenyc.research.designpattern.structure.composite.api.Employee;
import com.heelenyc.research.designpattern.structure.composite.api.MidEmployee;
import com.heelenyc.research.designpattern.structure.composite.impl.Manager;
import com.heelenyc.research.designpattern.structure.composite.impl.Programmer;

public class TestMain {

	public static void main(String[] args) {

		Employee zhansan = new Programmer("zhansan");
		MidEmployee lisi = new Manager("lisi");
		lisi.add(zhansan);
		Employee wangwu = new Programmer("wangwu");
		MidEmployee zhaoliu = new Manager("zhaoliu");
		zhaoliu.add(wangwu);
		
		MidEmployee boss = new Manager("Boss");
		boss.add(lisi);boss.add(zhaoliu);
		
		boss.show();
	}

}
