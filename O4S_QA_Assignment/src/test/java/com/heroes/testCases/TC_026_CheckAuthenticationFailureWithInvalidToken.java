package com.heroes.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.heroes.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_026_CheckAuthenticationFailureWithInvalidToken extends TestBase {
	
	@BeforeClass
public void CheckAuthenticationFail() throws InterruptedException 
	{
		logger.info("*********Started TC_026_CheckAuthenticationFailureWithInvalidToken **********");
		
		RestAssured.baseURI = "http://localhost:3000";
		httpRequest = RestAssured.given().auth().preemptive().oauth2(token1);
		
		

		response = httpRequest.request(Method.GET, "/heroes/HLK");
		
		Thread.sleep(2000);
		JsonPath jsonpath= response.jsonPath();
		System.out.println(jsonpath.get("statusCode"));
		System.out.println(jsonpath.get("error"));
		
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
		Assert.assertEquals(statusCode, 401);
	}
	
	
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 401 Unauthorized");
		
	}
	
	
	

	
	@AfterClass
	void tearDown()
	{
		logger.info("********* Finished TC_026_CheckAuthenticationFailureWithInvalidToken **********");
	}


}
