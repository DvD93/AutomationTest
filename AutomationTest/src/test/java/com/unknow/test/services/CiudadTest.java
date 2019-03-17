package com.unknow.test.services;

import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CiudadTest {
	
	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;

	
	@BeforeClass
    public static void Specification() {

        requestSpec = new RequestSpecBuilder().
            setBaseUri("http://api.zippopotam.us").
            build();
        
        responseSpec = new ResponseSpecBuilder().
	            expectStatusCode(200).
	            expectContentType(ContentType.JSON).
	            build();
    }
	
	@Test
    public void statusCodeTest() {

        given().
        	spec(requestSpec).
        when().
            get("ar/3400").
        then().
        	spec(responseSpec);
    }

    @Test
    public void contentTypeTest() {

        given().
        	spec(requestSpec).
        when().
            get("ar/3400").
        then().
        	spec(responseSpec);
    }

    @Test
    public void logRequestAndResponseTest() {

        given().
        	spec(requestSpec).
            log().all().
        when().
            get("ar/3400").
        then().
            log().body();
    }

    @Test
    public void stateNameInResponseBodyTest() {

        given().
        	spec(requestSpec).
        when().
            get("ar/3400").
        then().
            assertThat().
            body("places[0].state", equalTo("CORRIENTES"));
    }

    @Test
    public void listOfPlaceNamesInResponseBodyTest() {

        given().
        	spec(requestSpec).
        when().
            get("ar/3400").
        then().
            assertThat().
            body("places.'place name'", hasItem("CORRIENTES"));
    }

    @Test
    public void numberOfPlaceNamesInResponseBodyTest() {

        given().
        	spec(requestSpec).
        when().
            get("BR/99000-000").
        then().
            assertThat().
            body("places.'place name'", hasSize(1));
    }
    
    @DataProvider
	public static Object[][] zipCodesAndPlaces() {
		return new Object[][] {
            { "ar", "3400", "CORRIENTES" },
            { "ar", "3500", "TIGRE" },
            { "br", "01000-000", "São Paulo"} };
	}
    
    @Test(dataProvider = "zipCodesAndPlaces")
    public void placeNameInResponseBodyTest(String countryCode, String zipCode, String expectedPlaceName) {

        given().
        	spec(requestSpec).
            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
            get("{countryCode}/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(expectedPlaceName));
    }

   @Test
    public void placeNameInResponseBodyTest() {

        given().
        	spec(requestSpec).
        when().
            get("ar/3500").
        then().
            assertThat().
            body("places[0].'place name'", equalTo("TIGRE"));
    }

    @Test
    public void placeNameFromResponseBodyTest_assertEqualTo() {

        String placeName =

        given().
            spec(requestSpec).
        when().
            get("ar/3400").
        then().
            extract().
            path("places[0].'place name'");

        Assert.assertEquals(placeName, "CORRIENTES");
    }

}
