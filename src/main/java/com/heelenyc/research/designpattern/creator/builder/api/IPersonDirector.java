package com.heelenyc.research.designpattern.creator.builder.api;


public interface IPersonDirector {
	Person constructPerson(IPersonBuilder pb);
}
