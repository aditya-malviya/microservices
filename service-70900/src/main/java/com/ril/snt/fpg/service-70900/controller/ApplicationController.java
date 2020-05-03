package com.ril.snt.fpg.service70900.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class ApplicationController {
	private static Logger log = LoggerFactory.getLogger(ApplicationController.class);
		
	@Value("classpath:/dashboard.json")
    private Resource res;
    
	@GetMapping(value ="dashboard" , produces ="application/json")
	public String getDashboard()
	{
		log.info("Start My Dashboard ");
		
			JSONArray jarray = new JSONArray();
		
		try {
			jarray = Util.readJsonArrayFile(res.getInputStream());
				
			
		} catch (IOException e) {
				log .error(e.getMessage());	
			}
		
			return jarray.toJSONString();
		
		
	}
	
	@GetMapping(value="keepalive")
	public HttpStatus getKeepAlive() {
		
		return HttpStatus.OK;
	}
	
}
