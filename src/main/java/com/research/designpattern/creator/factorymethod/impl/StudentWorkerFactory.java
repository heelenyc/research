package com.research.designpattern.creator.factorymethod.impl;

import com.research.designpattern.creator.factorymethod.api.IWokerFactory;
import com.research.designpattern.creator.factorymethod.api.IWorker;

public class StudentWorkerFactory implements IWokerFactory {

	@Override
	public IWorker getWorker() {
		return new StudentWorker();
	}

}
