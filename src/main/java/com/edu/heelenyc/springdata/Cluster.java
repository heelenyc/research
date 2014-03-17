package com.edu.heelenyc.springdata;

import java.util.Set;

/**
 * @author yicheng
 * @since 2013年11月22日
 *
 */
public class Cluster {
	private String cluster;
	private Set<Instance> instances;
	private Instance inc;
	
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public Set<Instance> getInstances() {
		return instances;
	}
	public void setInstances(Set<Instance> instances) {
		this.instances = instances;
	}
	public Instance getInc() {
		return inc;
	}
	public void setInc(Instance inc) {
		this.inc = inc;
	}
}
