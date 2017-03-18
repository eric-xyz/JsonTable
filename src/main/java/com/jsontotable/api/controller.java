package com.jsontotable.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsontotable.model.HealthReport;
import com.jsontotable.service.JsonService;

@RestController
public class controller {
	
	private String htmlPage = "<html> <head> </head> <body> <h1>worked</h1> </body> </html>";
	
	@Autowired
	private JsonService jsonService;
	
	@RequestMapping(
		value = "api",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.TEXT_HTML_VALUE)
	public String getJson(@RequestBody Map<String, Object> input){
		//HealthReport newReport = jsonService.getHealthReport(input);
		return htmlPage;
		
//		return new ResponseEntity<HealthReport>(htmlPage, HttpStatus.OK);
	}
	
	

}
