package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
 * Given accept type is Json
 * And ıd parameter value is 6
 * When user sends GET request to /api/spartans/{id}
 * Then response status code should be 200
 * And response content-type: application/json
 * And "Tedmund" should be in the payload
 */


public class TC03 {

    @BeforeClass
    public void beforeClass(){
        baseURI= "http://54.166.122.207:8000";
    }


    @Test
    public void tc03(){

        given().accept(ContentType.JSON)
                .when().get("/api/spartans/6")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .body("name", equalTo("Tedmund"));
    }


}
