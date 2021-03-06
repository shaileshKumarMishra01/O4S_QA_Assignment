package com.heroes.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.heroes.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_033_CheckPostRequestWithValidIdaddedWithNumber extends TestBase{
	
	
    
	
	
	
	
	@BeforeClass
	void CheckPostSecondHero() throws InterruptedException
	{
		logger.info("*********Started TC_033_CheckPostRequestWithValidIdaddedWithNumber **********");	
		
		RestAssured.baseURI = "http://localhost:3000";
		httpRequest = RestAssured.given().auth().preemptive().oauth2(token);
		
		

		JSONObject requestParams = new JSONObject();
		requestParams.put("heroId", "THR1"); 
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, "/fight/addHero");
		
		Thread.sleep(5000);
		JsonPath jsonpath= response.jsonPath();
		System.out.println(jsonpath.get("errorCode"));
		System.out.println(jsonpath.get("error"));
		Thread.sleep(2000);
		
		
	}
	
	@Test
	void checkResposeBody()
		{
			String responseBody = response.getBody().asString();
            System.out.println(responseBody);

		}
			
		@Test
		void checkStatusCode()
		{
			int statusCode = response.getStatusCode(); 
			Assert.assertEquals(statusCode, 400);
		}
		
		@Test
		void checkResponseTime()
		{
			long responseTime = response.getTime(); 
			Assert.assertTrue(responseTime<5000);
			
		}
			
		@Test
		void checkstatusLine()
		{
			String statusLine = response.getStatusLine(); 
			Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
			
		}
		
		@Test
		void checkContentType()
		{
			String contentType = response.header("Content-Type");
			Assert.assertEquals(contentType, "application/json; charset=utf-8");
		}

		

		@Test
		void checkContentLength()
		{
			String contentLength = response.header("Content-Length");
			Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		
		}
		
      
	@AfterClass
	void tearDown()
	{
		logger.info("********* Finished TC_033_CheckPostRequestWithValidIdaddedWithNumber **********");
	}
	
	


}
