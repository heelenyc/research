package com.research.designpattern.creator.builder.impl;

import com.research.designpattern.creator.builder.api.IPersonBuilder;
import com.research.designpattern.creator.builder.api.IPersonDirector;
import com.research.designpattern.creator.builder.api.Person;

/**
 * 描述一种构造算法，自上而下构造
 * @author yicheng
 *
 */
public class PersonDirectorOfUpToDown implements IPersonDirector {

	@Override
	public Person constructPerson(IPersonBuilder pb) {
		pb.buildHeader();
		pb.buildFoot();
		pb.buildBody();
		return pb.getPerson();
	}

}
