package com.jsontotable.model;

import java.util.List;

public class HealthReport {
	private String overallServiceHealth;
	private String reason;
	private List<Dependency> dependencies;
	
	public String getOverallServiceHealth() {
		return overallServiceHealth;
	}
	public void setOverallServiceHealth(String overallServiceHealth) {
		this.overallServiceHealth = overallServiceHealth;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
	
	
}
