package com.synechron.ws.wstraining.basics.response;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class ValidatableResponseDemo {

	public String baseurl = "https://api.trello.com";
	public String key = "c5f676759b86029624f7dcb31ccf655e";
	public String token = "9b60bd7325defc221aa0203822765f426ecb134adcf46f7ca823c569ced3a2f3";
	private ValidatableResponse validatableResponse = null;
	@Test
	public void createValidataleResponseTest()
	{
		RestAssured.baseURI = baseurl;
				
		validatableResponse = given().
				param("key", key).
				param("token", token).
			when().
				get("/1/boards/llt4bIbC"). 
			then(). 
				assertThat().statusCode(200);
		
		String id  = validatableResponse.extract().path("id");
		String name = validatableResponse.extract().path("name");
		System.out.println("ID : "  + id);
		System.out.println("Name : "  + name);
	
	}
	
	
	
	@Test(dependsOnMethods = "createValidataleResponseTest")
	public void getNodeWithConditionTest() {
		Map<String,String> node = validatableResponse.extract().path("prefs.backgroundImageScaled.find {it.width == 67 }");
		System.out.println("Node " + node);
	}
	
	
	@Test(dependsOnMethods = "createValidataleResponseTest")
	public void getUrlFromNodeWithConditionTest() {
		String url = validatableResponse.extract().path("prefs.backgroundImageScaled.find {it.width == 67 }.url");
		System.out.println("URL " + url);
	}
	
	// retrieve the node which has max width
	@Test(dependsOnMethods = "createValidataleResponseTest")
	public void getNodeWithMaxConditionTest() {
		Map<String,String> node = validatableResponse.extract().path("prefs.backgroundImageScaled.max { it.width  }");
		System.out.println("MAX Width NODE :  " + node);
	}
	
	// retrieve the URL which has max width
		@Test(dependsOnMethods = "createValidataleResponseTest")
		public void getURLWithMaxConditionTest() {
			String url = validatableResponse.extract().path("prefs.backgroundImageScaled.max { it.width  }.url");
			System.out.println("url of - MAX Width NODE :  " + url);
		}
	
	// retrieve the node which has min width
	@Test(dependsOnMethods = "createValidataleResponseTest")
	public void getNodeWithMinConditionTest() {
		Map<String,String> node = validatableResponse.extract().path("prefs.backgroundImageScaled.min { it.width  }");
		System.out.println("MIN Width NODE :  " + node);
	}
	
	
	
}
