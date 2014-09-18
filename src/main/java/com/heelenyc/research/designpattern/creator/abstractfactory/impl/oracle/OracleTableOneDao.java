package com.heelenyc.research.designpattern.creator.abstractfactory.impl.oracle;

import com.heelenyc.research.designpattern.creator.abstractfactory.api.ITableOneDao;

public class OracleTableOneDao implements ITableOneDao {

	@Override
	public void Query() {
		System.out.println("query table one in oracle database!");
	}

}
