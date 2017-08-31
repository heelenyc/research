package com.heelenyc.research.misc.pecs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {

	public static void main(String[] args) {
		
		
		Stack<Number> s = new Stack<>();

//		s.push(new Integer(1));/* 参数不是集合的情况下，只要是类型可以替换，没有问题！ */
//		s.push(new Double(1));/* 参数不是集合的情况下，只要是类型可以替换，没有问题！ */
//		System.out.println(s.pop());
//		System.out.println(s.pop());
		
//		ArrayList<Integer> intlist = new ArrayList<>();
//		intlist.add(1);
//		intlist.add(2);
//		
//		/* 没有限定符的时候，编译报错 
//		Iterable<Number>) in the type Stack<Number> is not applicable for the arguments (ArrayList<Integer>*/
//		s.pushAll(intlist); 
//		System.out.println(s.pop());
		
//		Collection<Object> c = new ArrayList<>();
//		/* (Collection<Number>) in the type Stack<Number> is not applicable for the arguments (Collection<Object> */
//		s.popAll(c);
		
		Logger logger = LoggerFactory.getLogger(TestMain.class);
		logger.debug("hello " + "world!");
		logger.debug("{},{}","hello", "world!");
	}

}
