package com.research.designpattern.creator.abstractfactory.api;

/**
 * abstract factory defined a series interface of relative or inter-dependenced productor
 * @author yicheng
 *
 */
public interface IDBDaoFactory {
	ITableOneDao getTableOneDao();
	ITableTwoDao getTableTwoDao();
}
