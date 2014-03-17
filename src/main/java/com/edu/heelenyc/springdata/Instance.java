package com.edu.heelenyc.springdata;

/**
 * @author yicheng
 * @since 2013年11月22日
 *
 */
public class Instance extends baseObject implements Person {

	private String host;
	private int port;
	
	private transient String cluster;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	@Override
	public String getHigh() {
		return "i am 169cm";
	}
	
	public String getOwnGetter() {
		return "i am getOwnGetter";
	}
}
