package com.jsontotable;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsontotable.service.HtmlService;

@RunWith(SpringRunner.class)
public class HtmlServiceTest {
	 
	 private HtmlService htmlService; 
	 
	 @Before
	 public void setUp(){
		 htmlService = new HtmlService();
	 }

	 @Test
	 public void testPostWithNull() throws Exception {
		 String anticipated = "<body></body>";
		 String result = htmlService.getBody(null);
		 assertEquals(result,anticipated);
	 }
}
