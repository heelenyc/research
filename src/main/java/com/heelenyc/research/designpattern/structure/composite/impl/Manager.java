package com.heelenyc.research.designpattern.structure.composite.impl;

import com.heelenyc.research.designpattern.structure.composite.api.MidEmployee;

public class Manager extends MidEmployee {

	public Manager(String name) {
		super(name);
	}
	
	@Override
	public String getTitle(){
		return "manager";
	}

}
