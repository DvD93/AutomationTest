package com.unknow.test.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.unknow.model.Insumo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class InsumoTest {
	
	private Insumo insumo = new Insumo(3, "toner lasertjet 204A black", 10);
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
		RestAssured.basePath = "/api/insumo";
	}

	@Test
	public void getAll() {
		Response response = RestAssured.given().log().all().get();
		response.prettyPrint();
		
		//Compruebo que el resultado de la peticion sea correcto
		assertEquals(response.getStatusCode(), 200);
		JsonPath json = response.jsonPath();
		assertFalse(json.getList("insumos").isEmpty(), "Lista de insumos vacia.");
		assertEquals(json.getInt("insumos[1].id"), 2);
		assertEquals(json.getInt("insumos[1].cantidad"), 5);
		assertEquals(json.getString("insumos[1].nombre"), "papel A4");
	}
	
	@Test
	public void getByIdUsingPathParam() {
		Response response = RestAssured.given().log().all()
				.pathParam("id", "1").get("/{id}");
		response.prettyPrint();
		assertEquals(response.getStatusCode(), 200);
		JsonPath json = response.jsonPath();
		assertEquals(json.getInt("id"), 1);
		assertEquals(json.getInt("cantidad"), 6);
		assertEquals(json.getString("nombre"), "tinta 952");
	}
	
	@Test
	public void addProductBadRequest() {
		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON).post();
		response.prettyPeek();
		//No existe el recurso
		assertEquals(response.getStatusCode(), 400);
		JsonPath json = response.jsonPath();
		assertEquals(json.getString("message"), "bad Request");
	}
	
	@Test
	public void addProduct() {
		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON).body(insumo).post();
		response.prettyPrint();
		assertEquals(response.statusCode(), 201);
		JsonPath json = response.jsonPath();

		assertEquals(json.getString("message"), "El insumo se ha agregado");
	}
	
	@Test(dependsOnMethods = "addProduct")
	public void getByIdUsingQueryParam() {
		Response response = RestAssured.given().log().all()
				.queryParam("id", insumo.getId()).get();
		response.prettyPrint();
		assertEquals(response.statusCode(), 200);
		JsonPath json = response.jsonPath();

		assertEquals(json.getInt("id"), insumo.getId());
		assertEquals(json.getString("nombre"), insumo.getNombre());
		assertEquals(json.getInt("cantidad"), insumo.getCantidad());
	}
	
	@Test(dependsOnMethods = "getByIdUsingQueryParam")
	public void deleteProduct(){
		Response response = RestAssured.given().log().all()
				.pathParam("id", insumo.getId())
				.delete("/{id}");
		response.prettyPrint();
		assertEquals(response.statusCode(), 200);
		JsonPath json = response.jsonPath();
		assertEquals(json.getString("message"), "El insumo ha sido eliminado exitosamente.");
	}

}
