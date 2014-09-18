package com.heelenyc.research.designpattern.creator.abstractfactory;

import com.heelenyc.research.designpattern.creator.abstractfactory.api.IDBDaoFactory;
import com.heelenyc.research.designpattern.creator.abstractfactory.impl.oracle.OracleDaoFactory;


public class TestMain {

	public static void main(String[] args) {
		IDBDaoFactory daoFactory = new OracleDaoFactory();// 这个可以通过注入来倒置依赖。
		daoFactory.getTableOneDao().Query();
	}

}
