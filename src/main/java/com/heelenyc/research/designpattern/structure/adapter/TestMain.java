package com.heelenyc.research.designpattern.structure.adapter;

import com.heelenyc.research.designpattern.structure.adapter.api.Target;
import com.heelenyc.research.designpattern.structure.adapter.impl.Adaptee;
import com.heelenyc.research.designpattern.structure.adapter.impl.Adapter;


public class TestMain {

	public static void main(String args[]) {
		Target target = new Adapter(new Adaptee());
		target.targetMethod();
	}
	
}
