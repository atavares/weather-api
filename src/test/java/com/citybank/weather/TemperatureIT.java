package com.citybank.weather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemperatureIT {
	
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
	}
	
	@Test
	public void givenReturnResponseAndStatu200_WhenExistsCity() {	
		RestAssured.given()
				   		.basePath("/temperature/São Paulo")
				   		.accept(ContentType.JSON)
				   .when()
				   		.get()
				   .then()
				   		.body("celsius", Matchers.isA(Float.class))
				   		.body("farenheit", Matchers.isA(Float.class))
				   		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void givenReturnStatu404_WhenNotExistsCity() {	
		RestAssured.given()
				   		.basePath("/temperature/São Paulo1")
				   		.accept(ContentType.JSON)
				   .when()
				   		.get()
				   .then()
				   		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
}
