package com.research.designpattern.creator.builder.impl;

import com.research.designpattern.creator.builder.api.IPersonBuilder;
import com.research.designpattern.creator.builder.api.Person;


public class FatPersonBuilder implements IPersonBuilder {
	
	private Person p = new Person();

	@Override
	public void buildHeader() {
		p.setHeader("肥头大耳");
	}

	@Override
	public void buildBody() {
		p.setBody("虎背熊腰");
	}

	@Override
	public void buildFoot() {
		p.setFoot("大象腿");
	}

	@Override
	public Person getPerson() {
		return p;
	}
	
}
