package com.jsontotable.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsontotable.model.Dependency;
import com.jsontotable.model.HealthReport;

@Service
public class HtmlService {
	
	private static final String styleSheet = "<style> + table { font-family: arial, sans-serif; border-collapse: collapse;width: 100%;}" +
			  "td, th { border: 2px solid #dddddd; text-align: left; padding: 8px; text-align: center;}" + 
              ".headRow{ height: 100px; padding: 30px; font-size: 2em; text-align: left;}" +
              ".depRow{text-align: left; height: 60px; padding: 30px;}" +
              ".name{ font-size: bold; }" +
              ".healthy{ background-color: #42BD39;}" + 
              ".unhealthy{background-color: #F03737; }" + 
              ".healthyWithUnhealthySubService{ background-color: #F3C559;} </style>"; 
	private static final String healthyClassName = "healthy";
	private static final String healthyClassNameWithUnhealthySubService = "healthyWithUnhealthySubService";
	private static final String unhealthyClassName = "unhealthy";
	
	private static final String healthyStatusName = "Healthy";
	private static final String unhealthyStatusName = "Unhealthy";
	
	
	public String getHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head>");
		sb.append(getStyleSheet());
		sb.append("</head>");
		return sb.toString();
	}

	public String getBody(HealthReport newReport) {
		StringBuilder sb = new StringBuilder();
		sb.append("<body>");
		sb.append(getContent(newReport));
		sb.append("/<body>");
		return sb.toString();
	}
	public String getFooter() {
		return "";
	}
	private String getStyleSheet(){
		return styleSheet;
	}

	private String getContent(HealthReport newReport) {
		String serviceName = newReport.getServiceName() == null? "" : newReport.getServiceName();
		String overAllStatus = newReport.getOverallHealth().equalsIgnoreCase(healthyStatusName)? healthyStatusName : 
																										unhealthyStatusName;
		boolean isUnhealthySubServive = isUnhealthySubService(newReport.getDependencies());
		String statusClassName = overAllStatus.equalsIgnoreCase(healthyStatusName) ? healthyClassName : unhealthyClassName;
		if(isUnhealthySubServive && statusClassName.equalsIgnoreCase(healthyClassName)){
			statusClassName = healthyClassNameWithUnhealthySubService;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<table><thead><tr><th colspan=\"3\" class=\"headRow\">" + serviceName + "</th>");
		sb.append("<th colspan=\"2\" class=\"headRow " + statusClassName + "\">" + overAllStatus + "</th></tr>");
		sb.append("<tr><th>Service Name</th><th>Status</th><th>Url</th><th>Latency in ms</th><th>Reason</th></tr></thead>");
		sb.append("<tbody>");
		List<Dependency> depList = newReport.getDependencies();
		if(depList != null){
			for(Dependency dependency : depList){
				String depServiceName = dependency.getServiceName() == null? "" : dependency.getServiceName();
				String depHealthyStatus = dependency.getStatus() == null? "" : dependency.getStatus();
				String depLatency = dependency.getLatency() == null? "" : dependency.getLatency();
				String depEndpoint = dependency.getUrl() == null? "" : dependency.getUrl();
				String depFailReason = dependency.getDetails() == null? "" : dependency.getDetails();
				String depStatusClassName = depHealthyStatus.equalsIgnoreCase(healthyStatusName)? healthyClassName : 
																										unhealthyClassName;
				sb.append("<tr class=" + depStatusClassName + ">");
				sb.append("<td class=\"name\">" + depServiceName + "</td>");
				sb.append("<td>" + depHealthyStatus + "</td>");
				sb.append("<td>" + depEndpoint + "</td>");
				sb.append("<td>" + depLatency + "</td>");
				sb.append("<td>" + depFailReason + "</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</tbody></table>");
		return sb.toString();
                
	}

	private boolean isUnhealthySubService(List<Dependency> list) {
		for(Dependency dependency : list){
			if(dependency.getStatus().equalsIgnoreCase("Unhealthy")){
				return true;
			}
		}
		return false;
	}


}
