package com.research.designpattern.creator.factorymethod;

import com.research.designpattern.creator.factorymethod.api.IWokerFactory;
import com.research.designpattern.creator.factorymethod.impl.StudentWorkerFactory;
import com.research.designpattern.creator.factorymethod.impl.TeacherWorkerFactory;

public class TestMain {

	public static void main(String[] args) {
		IWokerFactory sw = new StudentWorkerFactory();
		sw.getWorker().doWork();
		
		IWokerFactory tw = new TeacherWorkerFactory();
		tw.getWorker().doWork();
	}

}
