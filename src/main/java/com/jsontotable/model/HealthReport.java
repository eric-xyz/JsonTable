package com.jsontotable.model;

import java.util.List;

public class HealthReport {
	private String overallHealth;
	private String serviceName;
	private List<Dependency> dependencies;
	
	public String getOverallHealth() {
		return overallHealth;
	}
	public void setOverallHealth(String overallHealth) {
		this.overallHealth = overallHealth;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
	
	@Override 
	public String toString(){
		return "Service Name" + serviceName + " : " + "overallHealth" + " : " + overallHealth; 
				
	}
	
}
