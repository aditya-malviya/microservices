package com.ril.snt.fpg.service70900.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	
	private Util(){}
	private static Logger log = LoggerFactory.getLogger(Util.class);

	public static JSONObject readJsonFile(String fileName) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try(FileReader  f = new FileReader(fileName)){
			jsonObject = (JSONObject)parser.parse(f);
			log.info("JSONOBJECT = "+jsonObject.toJSONString());
		
		} catch (FileNotFoundException e) {
			log.error("Required file not available.");
		} catch (IOException e) {
			log.error("IO exception occured..");
		} catch (ParseException e) {
			log.error(e.getMessage());	
		}		
		return jsonObject;
	}
	
	public static JSONArray readJsonArrayFile(InputStream in) {
		JSONParser parser = null;
		JSONArray jarray = null;
		try {
		    parser = new JSONParser();
			jarray = (JSONArray)parser.parse(new InputStreamReader(in, "UTF-8"));
			log.info("JSON Array = "+jarray.toJSONString());
		} catch (IOException | ParseException e) {
			log.error(e.getMessage());
		} 	
		
		return jarray;
	}
	
}
