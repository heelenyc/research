package com.heelenyc.research.designpattern.creator.factorymethod.impl;

import com.heelenyc.research.designpattern.creator.factorymethod.api.IWorker;

public class TeacherWorker implements IWorker {

	@Override
	public void doWork() {
		System.out.println("批改作业");
	}

}
