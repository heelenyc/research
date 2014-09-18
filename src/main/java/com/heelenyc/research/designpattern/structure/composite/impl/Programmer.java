package com.heelenyc.research.designpattern.structure.composite.impl;

import com.heelenyc.research.designpattern.structure.composite.api.Employee;

public class Programmer extends Employee {

	public Programmer(String name) {
		super(name);
	}

	@Override
	public String getTitle() {
		return "programmer";
	}

}
