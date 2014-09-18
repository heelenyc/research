package com.heelenyc.research.designpattern.creator.prototype;

public class PrototypeString implements Cloneable {
	
	private String value;
	
	
	public PrototypeString(String value) {
		super();
		this.value = value;
	}
	
	public void say() {
		System.out.println(value);
	}



	public PrototypeString clone() {
		try {
			return (PrototypeString)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (null != value) {
			return value.equals(((PrototypeString) obj).getValue());		
		}
		return super.equals(obj);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
