package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class review{
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


    @BeforeClass
    public void initalConnection(){
        baseURI= "http://54.166.122.207:8000";
    }



    @Test
    public void review1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 42)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("id"),42);
        Assert.assertEquals(jsonPath.getString("name"),"Atlante");
        Assert.assertEquals(jsonPath.getString("gender"),"Female");
        Assert.assertEquals(jsonPath.getLong("phone"),4579688654L);


    }



}

