package com.heroes.TestBase;

import java.util.logging.Logger;


import org.testng.annotations.BeforeClass;
import org.apache.log4j.PropertyConfigurator;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
    public String token="pag4nt1stoken";
    public String token1="pag4nt1stoken1";
    
	public Logger logger;
	
	@BeforeClass
	public void setup(){
		
		logger=Logger.getLogger("HeroesRestAPI");
		PropertyConfigurator.configure("Log4j.properties"); 
	    httpRequest = RestAssured.given().auth().preemptive().oauth2(token);
	}
	
	
	
	
}
