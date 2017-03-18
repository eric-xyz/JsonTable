package com.jsontotable.service;

import org.springframework.stereotype.Service;

import com.jsontotable.model.HealthReport;

@Service
public class HtmlService {
	
	public String getHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head>");
		sb.append(getStyleSheet());
		sb.append("</head>");
		return null;
	}

	public String getBody(HealthReport newReport) {
		StringBuilder sb = new StringBuilder();
		sb.append("<body>");
		sb.append(getContent(newReport));
		sb.append("/<body>");
		return null;
	}
	public String getFooter() {
		return "";
	}
	private String getStyleSheet(){
		return "";
	}

	private String getContent(HealthReport newReport) {
		
		return "";
	}


}
