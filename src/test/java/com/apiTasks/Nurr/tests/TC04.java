package com.apiTasks.Nurr.tests;

/*
           * When user sends a get request to api/spartans/3
           * Then status code should be 200
           * And content type should be application/json;charset=UFT-8
           * And name should contain Fidole
           * (use jsonPath method)

           ( u can try this data as well...
           id   name    phone       gender
           7	Hershel 5278678322	Male
           12	Sol     7006438852	Male
           29	Dorelle	6687391932	Female
           67	Janette	9887020445	Female
           )
 */

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class TC04 {

    @BeforeClass
    public void initalConnection(){
        baseURI= "http://54.166.122.207:8000";
    }

    @Test
    public void tc04(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 12)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();

        String jsonName= response.jsonPath().getString("name");
       // System.out.println(jsonName);

        int jsonId= response.jsonPath().getInt("id");
       // System.out.println(jsonId);

        String jsonGender= response.jsonPath().getString("gender");
       // System.out.println(jsonGender);

        long jsonPhone= response.jsonPath().getLong("phone");
       // System.out.println(jsonPhone);

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");
       assertEquals(jsonId,12);
       assertEquals(jsonName,"Sol");
       assertEquals(jsonGender,"Male");
       assertEquals(jsonPhone,7006438852L);
    }


}
