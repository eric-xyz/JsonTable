package com.jsontotable;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jsontotable.api.Controller;

public class SetupTest {
	
	 
	private static Controller controller;
	
	@Before
	public void setup(){
		controller = new Controller();
	}
	
	@Test
    public void createServiceSuccessfully() throws Exception {
		assertNotNull(controller);
    }
	
	
}
