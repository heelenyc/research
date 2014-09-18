package com.heelenyc.research.designpattern.structure.adapter.impl;

import com.heelenyc.research.designpattern.structure.adapter.api.Target;

public class Adapter implements Target {
	
	private Adaptee adaptee;
	
	public Adapter(Adaptee adapter) {
		this.adaptee = adapter;
	}

	@Override
	public void targetMethod() {
		System.out.println("in adapter");
		adaptee.AdapteeMethod();
	}

}
