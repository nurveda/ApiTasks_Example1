package com.apiTasks.Osman.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class TC06 {
    /*TC06 --> spartanApi
       given accept type is Json
             And path param id is 15
             When user sends a get request to spartans/{id}
             Then status code is 200
             And content type is Json
             And json data has following
                 "id": 15,
                 "name": "Meta",
                 "gender": "Female",
                 "phone": 1938695106

     */

    @BeforeClass
    public void beforeClass(){baseURI ="http://3.86.189.171:8000/";}

    @Test
    public void task6(){

        Response response = given().pathParam("id",15)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        assertEquals(id,15);

        String name = jsonPath.getString("name");
        assertEquals(name,"Meta");

        String gender = jsonPath.getString("gender");
        assertEquals(gender,"Female");

        long phone = jsonPath.getLong("phone");
        assertEquals(phone,1938695106L);


    }

}
