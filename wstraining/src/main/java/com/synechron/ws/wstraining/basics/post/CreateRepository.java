package com.synechron.ws.wstraining.basics.post;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CreateRepository {
	
	
	String baseurl = "https://api.github.com";
	String barrierToken = "Bearer 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";
	
	@Test
	public void validateCreateRepository()
	{
		RestAssured.baseURI = baseurl;
		RestAssured.given()
						.headers("Authorization", barrierToken)
						.headers("Content-type", "application/json")
						.body("{\r\n" + 
								"    \"name\" : \"RepoFromAPI1234\",\r\n" + 
								"    \"description\" : \"First POST Using Body\"\r\n" + 
								"}")
						.when()
							.post("/user/repos")
						.then()
							.assertThat().statusCode(201).and()
							.body("name", CoreMatchers.equalTo("RepoFromAPI1234"));
	}

}
