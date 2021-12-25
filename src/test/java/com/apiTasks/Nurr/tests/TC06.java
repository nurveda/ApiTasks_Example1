package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class TC06 {


    @BeforeClass
    public void initialConnection(){
        baseURI= "http://54.166.122.207:8000";
    }

    @Test
    public void tc06(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 44)
                .when().get("api/spartans/{id}");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        assertEquals(response.jsonPath().getInt("id"),44);
        assertEquals(response.jsonPath().getString("name"),"Panchito");
        assertEquals(response.jsonPath().getString("gender"),"Male");
        assertEquals(response.jsonPath().getLong("phone"),7486765293L);


    }




}
