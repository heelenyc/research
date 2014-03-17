package com.research.designpattern.creator.abstractfactory.impl.mysql;

import com.research.designpattern.creator.abstractfactory.api.ITableTwoDao;

public class MySQLTableTwoDao implements ITableTwoDao {

	@Override
	public void Query() {
		System.out.println("query table two in mysql database!");
	}

}
