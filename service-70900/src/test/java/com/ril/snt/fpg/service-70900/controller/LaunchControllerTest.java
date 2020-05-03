package com.ril.snt.fpg.service70900.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



@SpringBootTest
@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
public class LaunchControllerTest {
	private static Logger log = LoggerFactory.getLogger(LaunchControllerTest.class);

	private MockMvc mockMvc;
	
	@Mock
	private Resource res;
	
	@InjectMocks
	private ApplicationController appController;

	 @Before
	 @PrepareForTest(Util.class)
	 public void setUp()  {
	  try{
	       MockitoAnnotations.initMocks(this);
	       this.mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
	       PowerMockito.mockStatic(Util.class);
			
	       JSONArray jarray = mockJson();
	       when(Util.readJsonArrayFile(Mockito.any())).thenReturn(jarray);
	        }catch(Exception e){log .error(e.getMessage());}
	    }

	 private JSONArray mockJson()   {
	    String strJSON="";
	    try{
		  strJSON = "[ \r\n" + 
		 		"  {\r\n" + 
		 		"    \"title\": \"Central Platform\",\r\n" + 
		 		"    \"description\": \"Central Platform microservice R-fabric framework\",\r\n" + 
		 		"    \"version\": \"1.0.0\",\r\n" + 
		 		"    \"organization\":\"RIL\"\r\n" + 
		 		"  }\r\n" + 
		 		"]"; 
		 		
		 		return (JSONArray) new JSONParser().parse(strJSON);
		 	}	
		 catch(Exception pe){
	         return null;
	     }
		
	 
	 }
		@Test 
	public void dashboard()  {
	
	 try{
		
 	MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/dashboard").accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		log.info("Test dashboard="+resp.getStatus()+" ==>"+resp.getContentAsString());
		assertNotNull(resp);
		
		assertThat(resp.getContentAsString(), containsString("RIL"));
		
		 }catch(Exception e){log .error(e.getMessage());}
		
	}


	
}
