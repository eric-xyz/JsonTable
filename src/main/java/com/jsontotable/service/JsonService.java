package com.jsontotable.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsontotable.model.Dependency;
import com.jsontotable.model.HealthReport;

@Service
public class JsonService {
	private static final String _overallHealth = "overallHealth";
	private static final String _serviceName = "serviceName";
	private static final String _dependencies = "dependencies";
	private static final String _depServiceName = "serviceName";
	private static final String _serviceHealth = "healthy";
	private static final String _serviceUrl = "endpoint";
	private static final String _serviceLatency = "latency";
	private static final String _serviceDetails = "details";
	
	@Autowired
	HtmlService htmlService;
	
	public HealthReport getHealthReport(Map<String, Object> input){
		HealthReport report = new HealthReport();
		String serviceHealth = (String)input.get(_overallHealth);
		if(null != serviceHealth && !serviceHealth.isEmpty()){
			report.setOverallHealth(serviceHealth);
		}
		String serviceName = (String)input.get(_serviceName);
		if(null != serviceName && !serviceName.isEmpty()){
			report.setServiceName(serviceName);
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<LinkedHashMap<String, Object>> ls = 
				(ArrayList<LinkedHashMap<String, Object>>)input.get(_dependencies);
		List<Dependency> depList = getDependencyList(ls);
		report.setDependencies(depList);
		return report;
	}

	private List<Dependency> getDependencyList(List<LinkedHashMap<String, Object>> ls) {
		List<Dependency> res = new ArrayList<>();
		if(ls == null || ls.size() == 0) return res;
		for(int i = 0; i < ls.size(); ++i){
			Dependency tempDependency = new Dependency();
			LinkedHashMap<String, Object> map = ls.get(i);
			for(Map.Entry<String, Object> entry : map.entrySet()){
				if(entry.getValue() instanceof String){
					setField(tempDependency, entry.getKey(), entry.getValue().toString());
				}else{
					if(null == entry.getValue()){
						setField(tempDependency, entry.getKey(), "");
					}else{
						setField(tempDependency, entry.getKey(), entry.getValue().toString());
					}
				}
			}
			res.add(tempDependency);
		}	
		return res;
	}

	private void setField(Dependency tempDependency, String fieldName, String filedValue) {
		// TODO Auto-generated method stub
		switch(fieldName){
			case _depServiceName:
				tempDependency.setServiceName(filedValue);
				break;
			case _serviceHealth:
				tempDependency.setStatus(filedValue);
				break;
			case _serviceUrl:
				tempDependency.setUrl(filedValue);
				break;
			case _serviceLatency:
				tempDependency.setLatency(filedValue);
				break;
			case _serviceDetails:
				tempDependency.setDetails(filedValue);
				break;
		}	
	}

	public String getHtmlPage(HealthReport newReport) {
		StringBuilder htmlPage = new StringBuilder();
		htmlPage.append(htmlService.getHeader());
		htmlPage.append(htmlService.getBody(newReport));
		htmlPage.append(htmlService.getFooter());
		return htmlPage.toString();
	}
}
