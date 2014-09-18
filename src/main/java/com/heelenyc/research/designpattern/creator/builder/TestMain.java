package com.heelenyc.research.designpattern.creator.builder;

import com.heelenyc.research.designpattern.creator.builder.api.Person;
import com.heelenyc.research.designpattern.creator.builder.impl.FatPersonBuilder;
import com.heelenyc.research.designpattern.creator.builder.impl.PersonDirectorOfUpToDown;

public class TestMain {

	public static void main(String[] args) {
		Person pd = new PersonDirectorOfUpToDown().constructPerson(new FatPersonBuilder());
		System.out.println(pd);
	}
}
