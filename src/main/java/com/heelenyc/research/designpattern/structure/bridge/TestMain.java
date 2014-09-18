package com.heelenyc.research.designpattern.structure.bridge;

import com.heelenyc.research.designpattern.structure.bridge.api.Vehcile;
import com.heelenyc.research.designpattern.structure.bridge.impl.Car;
import com.heelenyc.research.designpattern.structure.bridge.impl.FardEngin;


public class TestMain {

	public static void main(String[] args){
		Vehcile v = new Car();
		v.assembleEngin(new FardEngin());
		
		v.run();
	}
}
