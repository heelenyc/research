package com.research.designpattern.creator.abstractfactory.impl.mysql;

import com.research.designpattern.creator.abstractfactory.api.ITableOneDao;

public class MySQLTableOneDao implements ITableOneDao {

	@Override
	public void Query() {
		System.out.println("query table one in mysql database!");
	}

}
