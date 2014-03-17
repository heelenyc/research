package com.research.designpattern.creator.builder.api;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 一个实例的具体值和构造是分离的，就是说person 这个bean本身new的时候不知道自己的各部分是怎么构造的。
 * @author yicheng
 *
 */
public class Person {
	
	private String header;
	private String body;
	private String foot;
	private Queue<String> construList = new LinkedBlockingQueue<String>();

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		construList.add(header);
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		construList.add(body);
		this.body = body;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		construList.add(foot);
		this.foot = foot;
	}

	@Override
	public String toString() {
		return construList.toString();
	}
}
