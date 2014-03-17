package com.research.designpattern.creator.builder;

import com.research.designpattern.creator.builder.api.Person;
import com.research.designpattern.creator.builder.impl.FatPersonBuilder;
import com.research.designpattern.creator.builder.impl.PersonDirectorOfUpToDown;

public class TestMain {

	public static void main(String[] args) {
		Person pd = new PersonDirectorOfUpToDown().constructPerson(new FatPersonBuilder());
		System.out.println(pd);
	}
}
