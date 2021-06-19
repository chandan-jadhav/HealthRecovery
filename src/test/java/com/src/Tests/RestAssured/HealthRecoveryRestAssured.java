package com.src.Tests.RestAssured;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.http.*;
import org.hamcrest.Matchers.*;

public class HealthRecoveryRestAssured {
	
	@BeforeTest()
	public void setUp() {
		baseURI = "https://cc.healthrecoverysolutions.com";
	}
	
	@Test
	public void verifyLoginPageIsDisplayed() {
		
		basePath = "/login";
		
		given().
			accept(ContentType.XML).
		when().
			get().
		then().
			assertThat().
			statusCode(304);
	}
	
	@Test
	public void verifyInvalidLoginCrendials() {
		
		basePath = "/login";
		
		given().
			accept(ContentType.XML).
			auth().
			basic("cjadhav@gmail.com", "pass123").
		when().
			get().
		then().
			assertThat().
			statusCode(401);
		
	}
	
	@AfterTest()
	public void tearDown() {
		
	}
	
}
