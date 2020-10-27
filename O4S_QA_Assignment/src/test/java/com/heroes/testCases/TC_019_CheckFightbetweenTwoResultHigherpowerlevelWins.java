package com.heroes.testCases;


	

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.heroes.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_019_CheckFightbetweenTwoResultHigherpowerlevelWins  extends TestBase {


@BeforeClass
	void CHeckFIght() throws InterruptedException
	    	{
			logger.info("*********Started TC_019_CheckFightbetweenTwoResultHigherpowerlevelWins **********");
			
	    	
		
			RestAssured.baseURI = "http://localhost:3000";
			httpRequest = RestAssured.given().auth().preemptive().oauth2(token);

			JSONObject requestParams4 = new JSONObject();
			httpRequest.header("Content-Type", "application/json");
			httpRequest.body(requestParams4.toJSONString());
			response = httpRequest.request(Method.DELETE, "/fight");
			
	
			// Posting 1st Hero
			JSONObject requestParams = new JSONObject();
			requestParams.put("heroId", "IRM"); 
			httpRequest.header("Content-Type", "application/json");
			httpRequest.body(requestParams.toJSONString());
			response = httpRequest.request(Method.POST, "/fight/addHero");
				
			
			// Posting 2nd Hero
		        JSONObject requestParams1 = new JSONObject();
		        requestParams1.put("heroId", "CAM"); 
		    	httpRequest.header("Content-Type", "application/json");
		    	httpRequest.body(requestParams1.toJSONString());
		    	response = httpRequest.request(Method.POST, "/fight/addHero");
		    	
		    	//Fight
				JSONObject requestParams2 = new JSONObject();
				httpRequest.header("Content-Type", "application/json");
				httpRequest.body(requestParams2.toJSONString());
				response = httpRequest.request(Method.POST, "/fight");
				
				Thread.sleep(5000);
				JsonPath jsonpath= response.jsonPath();
				System.out.println(jsonpath.get("message"));
				
				
				
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
			Assert.assertEquals(statusCode, 200);
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
		
		@Test
				void checkResponseTime()
				{
					long responseTime = response.getTime();
					Assert.assertTrue(responseTime<5000);
					
				}
		
		
		@AfterClass
		void tearDown()
		{
			logger.info("********* Finished TC_019_CheckFightbetweenTwoResultHigherpowerlevelWins **********");
		}



}
