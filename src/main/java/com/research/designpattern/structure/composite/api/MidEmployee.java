package com.research.designpattern.structure.composite.api;

import java.util.ArrayList;
import java.util.List;

public abstract class MidEmployee extends Employee {

	public MidEmployee(String name) {
		super(name);
	}

	private List<Employee> subList = new ArrayList<>();

	public Employee add(Employee e){
		subList.add(e);
		return this;
	}
	
	public Employee remove(Employee e){
		subList.remove(e);
		return this;
	}
	
	public void show(){
		System.out.println("I am " + getTitle() + " " + getName());
		for(Employee item : subList){
			item.show();
		}
	}

}
