package com.apiTasks.Osman.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class TC02 {
    /* TC02 --> spartanApi
        * Given accept type is json
        * And path param id is 45 (do not use this num, use different number)
            * When user sends a get request to "api/spartans/{id}"
            * Then status code is 200
            * And content-type is "application/json"
            * And response payload values match the following:
   "id": 45,
    "name": "Heddie",
    "gender": "Female",
    "phone": 1271425843
            (use path method)
     */
    @Test
    public void task02(){

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id","45")
                .when().get("http://3.86.189.171:8000/api/spartans/{id}");

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");

       int id = response.path("id");
        assertEquals(id,45);

        String name = response.path("name");
        assertEquals(name,"Heddie");

        String gender = response.path("gender");
        assertEquals(gender,"Female");

        int phone = response.path("phone");
        assertEquals(phone,1271425843);

    }


}
