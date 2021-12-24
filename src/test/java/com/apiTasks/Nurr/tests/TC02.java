package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

/*
        * Given accept type is json
                * And path param id is 77
                * When user sends a get request to "api/spartans/{id}"
                * Then status code is 200
                * And content-type is "application/json;charset=UTF-8"
                * And response payload values match the following:
                id is 77,
                name is "Stevana",
                gender is "Female",
                phone is 1459126818
*/

public class TC02 {

    @BeforeClass
    public void initalConnection(){
        baseURI= "http://54.166.122.207:8000";
    }


    @Test
    public void tc02(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 77)
                .when().get("/api/spartans/{id}");

        response.body().prettyPrint();

        int actualId= response.path("id");
        String actualName= response.path("name");
        String actualGender= response.path("gender");
        int actualPhone= response.path("phone");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertEquals(77, actualId);
        assertEquals("Stevana", actualName);
        assertEquals("Female", actualGender);
        assertEquals(1459126818, actualPhone);





    }

}
