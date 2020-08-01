package br.sp.fegodinho.rest.tests.refac;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class ContasTest {
	
	@BeforeClass
	public static void login() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "fegodinho@godinho");
		login.put("senha", "123456");
		
		//obtendo token de acesso
		String TOKEN =  given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token")
		;
		
		RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
		
		RestAssured.get("/reset").then().statusCode(200);
	}	

}
