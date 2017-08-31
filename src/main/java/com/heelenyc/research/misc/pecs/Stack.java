package com.heelenyc.research.misc.pecs;

import java.util.ArrayList;
import java.util.Collection;

public class Stack<E> {

	private ArrayList<E> elementList;

	public Stack() {
		elementList = new ArrayList<E>();
	}

	public void push(E e) {
		elementList.add(e);
	}

	public E pop() {
		if (elementList.size() <= 0) {
			return null;
		}
		return elementList.remove(0);
	}

	public boolean isEmpty() {
		return elementList.isEmpty();
	}

	/*
	 * java 集合的协变问题，如果参数是Iterable<E>，则实参必须是Iterable<E>，否则编译类型不匹配的问题 ? extends E
	 * 编译可以通过，这个时候参数表达的意思是producer，即是用来被访问的，不确定里面会有些什么，但是可以确定都是E的子类 ? super E
	 * 编译不通过没法按E类型读取
	 * 总结：src是用来访问读取的，里面的东西肯定是E的替代者，所以用？extends 限定符
	 */
	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			elementList.add(e);
		}
	}

	/*
	 * 总结：dst 参数使用来放东西的，既然是往里面放，只要里面的类型只要求是E的父类就行，所以用？super 限定符
	 */
	public void popAll(Collection<? super E> dst) {
		if (!isEmpty())
			dst.add(pop());
	}
}
