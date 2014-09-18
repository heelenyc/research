package com.heelenyc.research.designpattern.creator.factorymethod.api;

/**
 * 由具体的子类去决定创建何种实例
 * @author yicheng
 *
 */
public interface IWokerFactory {
	IWorker getWorker();
}
