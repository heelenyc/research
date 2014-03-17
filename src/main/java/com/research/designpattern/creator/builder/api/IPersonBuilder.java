package com.research.designpattern.creator.builder.api;


public interface IPersonBuilder {

	void buildHeader();
	void buildBody();
	void buildFoot();
	
	Person getPerson();
}
