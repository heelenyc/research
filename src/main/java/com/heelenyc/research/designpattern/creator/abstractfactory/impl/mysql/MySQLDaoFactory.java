package com.heelenyc.research.designpattern.creator.abstractfactory.impl.mysql;

import com.heelenyc.research.designpattern.creator.abstractfactory.api.IDBDaoFactory;
import com.heelenyc.research.designpattern.creator.abstractfactory.api.ITableOneDao;
import com.heelenyc.research.designpattern.creator.abstractfactory.api.ITableTwoDao;

public class MySQLDaoFactory implements IDBDaoFactory {

	@Override
	public ITableOneDao getTableOneDao() {
		return new MySQLTableOneDao();
	}

	@Override
	public ITableTwoDao getTableTwoDao() {
		return new MySQLTableTwoDao();
	}

}
