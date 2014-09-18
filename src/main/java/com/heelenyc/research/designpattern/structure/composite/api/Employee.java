package com.heelenyc.research.designpattern.structure.composite.api;


public abstract class Employee {
	
	private String name;
	
	public Employee(String name){
		this.name = name;
	}
	
	public void show(){
		System.out.println("I am " + getTitle() + " " + getName());
	}

	public abstract String getTitle();
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
