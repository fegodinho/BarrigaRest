package br.sp.fegodinho.rest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.sp.fegodinho.rest.core.BaseTest;

public class BarrigaTest extends BaseTest {
	
	@Test
	public void naoDeveAcessarAPISemToken() {
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401)
		;
	}
	
	@Test
	public void deveIncluirContaComSucesso() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "fegodinho@godinho");
		login.put("senha", "123456");
		
		//obtendo token de acesso
		String token =  given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		;
		
		given()
			.header("Authorization", "JWT " + token)
			.body("{\"nome\": \"conta qualquer\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
		;
		
	}

}
