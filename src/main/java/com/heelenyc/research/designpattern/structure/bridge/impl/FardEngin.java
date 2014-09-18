package com.heelenyc.research.designpattern.structure.bridge.impl;

import com.heelenyc.research.designpattern.structure.bridge.api.Engin;

public class FardEngin implements Engin {

	private String name = "fard engin";
	@Override
	public void work() {
		System.out.println(getName()+" works!");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
