package com.heelenyc.research.designpattern.creator.factorymethod.impl;

import com.heelenyc.research.designpattern.creator.factorymethod.api.IWokerFactory;
import com.heelenyc.research.designpattern.creator.factorymethod.api.IWorker;

public class TeacherWorkerFactory implements IWokerFactory {

	@Override
	public IWorker getWorker() {
		return new TeacherWorker();
	}

}
