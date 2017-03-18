package com.jsontotable.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jsontotable.model.Dependency;
import com.jsontotable.model.HealthReport;

@Service
public class JsonService {
	private static final String overallHealth = "overallHealth";
	private static final String serviceName = "serviceName";
	private static final String dependencies = "dependencies";
	private static final String serviceHealth = "healthy";
	private static final String serviceUrl = "endpoint";
	private static final String serviceLatency = "latency";
	private static final String serviceDetails = "reason";
	
	public HealthReport getHealthReport(Map<String, Object> input){
		HealthReport report = new HealthReport();
		String serviceHealth = (String)input.get(overallHealth);
		if(null != serviceHealth && !serviceHealth.isEmpty()){
			report.setOverallServiceHealth(serviceHealth);
		}
		String failedReason = (String)input.get(serviceName);
		if(null != failedReason && !failedReason.isEmpty()){
			report.setReason(failedReason);
		}
		System.out.println(((ArrayList)input.get(dependencies)).get(0).getClass().getName());
		
		@SuppressWarnings("unchecked")
		ArrayList<LinkedHashMap<String, LinkedHashMap<String,String>>> ls = 
				(ArrayList<LinkedHashMap<String, LinkedHashMap<String,String>>>)input.get(dependencies);
		
		List<Dependency> depList = getDependencyList(ls);
		report.setDependencies(depList);
		return report;
	}

	private List<Dependency> getDependencyList(List<LinkedHashMap<String, LinkedHashMap<String, String>>> ls) {
		List<Dependency> res = new ArrayList<>();
		if(ls == null || ls.size() == 0) return res;
		LinkedHashMap<String, LinkedHashMap<String, String>> map = ls.get(0);
		for(String key : map.keySet()){
			Dependency tempDependency = new Dependency();
			LinkedHashMap<String, String> contentMap = map.get(key);
			tempDependency.setServiceName(key);
			for(String contentKey : contentMap.keySet()){
				String contentValue = contentMap.get(contentKey);
				if(contentKey.equals(serviceHealth)){
					tempDependency.setStatus(contentValue);
				}else if(contentKey.equals(serviceUrl)){
					tempDependency.setUrl(contentValue);
				}else if(contentKey.equals(serviceLatency)){
					tempDependency.setLatency(contentValue);
				}else if(contentKey.equals(serviceDetails)){
					tempDependency.setDetails(contentValue);
				}
			}
			res.add(tempDependency);
		}
		return res;
	}
}
