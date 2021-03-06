package com.heelenyc.research.designpattern.creator.abstractfactory.impl.oracle;

import com.heelenyc.research.designpattern.creator.abstractfactory.api.IDBDaoFactory;
import com.heelenyc.research.designpattern.creator.abstractfactory.api.ITableOneDao;
import com.heelenyc.research.designpattern.creator.abstractfactory.api.ITableTwoDao;

public class OracleDaoFactory implements IDBDaoFactory {

	@Override
	public ITableOneDao getTableOneDao() {
		return new OracleTableOneDao();
	}

	@Override
	public ITableTwoDao getTableTwoDao() {
		return new OracleTableTwoDao();
	}

}
