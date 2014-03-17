package com.research.designpattern.creator.builder.api;


public interface IPersonDirector {
	Person constructPerson(IPersonBuilder pb);
}
