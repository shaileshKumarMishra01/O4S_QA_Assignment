package com.heroes.testCases;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.heroes.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


public class TC_005_CheckPostRequestMakeThemFight extends TestBase {
	
   	
	
	
	@BeforeClass
	void CheckPostFight() throws InterruptedException
	{
		logger.info("********* Started TC_005_PostRequestMakeThemFight **********");	
		
		RestAssured.baseURI = "http://localhost:3000";
		httpRequest = RestAssured.given().auth().preemptive().oauth2(token);
		
		

		JSONObject requestParams = new JSONObject();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, "/fight");
		
		Thread.sleep(2000);
		
		JsonPath jsonpath= response.jsonPath();
		System.out.println(jsonpath.get("message"));
		
		
		
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
			Assert.assertEquals(statusCode, 200);
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
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			
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
		logger.info("********* Finished TC_005_PostRequestMakeThemFight **********");
	}
	

}




