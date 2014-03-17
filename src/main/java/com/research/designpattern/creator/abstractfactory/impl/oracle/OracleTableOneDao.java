package com.research.designpattern.creator.abstractfactory.impl.oracle;

import com.research.designpattern.creator.abstractfactory.api.ITableOneDao;

public class OracleTableOneDao implements ITableOneDao {

	@Override
	public void Query() {
		System.out.println("query table one in oracle database!");
	}

}
