package com.research.designpattern.structure.adapter;

import com.research.designpattern.structure.adapter.api.Target;
import com.research.designpattern.structure.adapter.impl.Adaptee;
import com.research.designpattern.structure.adapter.impl.Adapter;


public class TestMain {

	public static void main(String args[]) {
		Target target = new Adapter(new Adaptee());
		target.targetMethod();
	}
	
}
