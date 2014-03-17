package com.research.designpattern.creator.abstractfactory.impl.oracle;

import com.research.designpattern.creator.abstractfactory.api.ITableTwoDao;

public class OracleTableTwoDao implements ITableTwoDao {

	@Override
	public void Query() {
		System.out.println("query table two in oracle database!");
	}

}
