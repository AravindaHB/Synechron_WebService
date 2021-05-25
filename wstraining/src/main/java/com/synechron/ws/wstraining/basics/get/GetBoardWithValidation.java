package com.synechron.ws.wstraining.basics.get;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;

import static io.restassured.RestAssured.*;

public class GetBoardWithValidation {

	public String baseurl = "https://api.trello.com";
	public String key = "c5f676759b86029624f7dcb31ccf655e";
	public String token = "9b60bd7325defc221aa0203822765f426ecb134adcf46f7ca823c569ced3a2f3";
	
	@Test
	public void validateBodyInGet()
	{
		RestAssured.baseURI = baseurl;
		given().
			param("key", key).
			param("token", token).
		when().
			get("/1/boards/llt4bIbC"). 
		then(). 
			assertThat().statusCode(200).
			and(). 
				contentType(ContentType.JSON). 
			and().
				body("id", equalTo("60ac71721dceec66fea94ff9")).
			and().
				body("name", equalTo("myAutomationBoard"));
	
	}
	
	
	@Test
	public void validateResponseHeaderInGet()
	{
		RestAssured.baseURI = baseurl;
		given().
			param("key", key).
			param("token", token).
		when().
			get("/1/boards/llt4bIbC"). 
		then(). 
			assertThat().statusCode(200).
			and(). 
				contentType(ContentType.JSON). 
			and().
				header("Content-Type", "application/json; charset=utf-8"). 
			and(). 
				header("Expires", "Thu, 01 Jan 1970 00:00:00"). 
			and(). 
				body("id", equalTo("60ac71721dceec66fea94ff9")).
			and().
				body("name", equalTo("myAutomationBoard"));
	
	}
}
