package com.apiTasks.Nurr.tests;


import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
 TC01 --> spartanApi
 Given accept type is json
 And path param spartan id is 42
 When user sends a get request to /spartans/{id}
 Then status code is 200
 And content type is Json
 And
    "id": 42,
    "name": "Atlante",
    "gender": "Female",
    "phone": 4579688654
 */


public class TC01 {

    @BeforeClass
    public void initalConnection(){
        baseURI= "http://54.166.122.207:8000";
    }

    @Test
    public void tc01(){

        given().accept(ContentType.JSON)
                .and().log().all().pathParam("id", 42)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().body("id",equalTo(42),
                        "name",equalTo("Atlante"),
                        "gender", equalTo("Female"),
                        "phone",equalTo(4579688654L));
    }



}
