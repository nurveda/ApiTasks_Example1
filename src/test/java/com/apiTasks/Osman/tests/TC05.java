package com.apiTasks.Osman.tests;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05 {
 /*   TC05 --> HRApi
               * Given, accept type is json
               * When user sends get request to regions/3
            * Then response status code is must be 200
            * and body format is json
               * and response body contains Asia

  */
    @Test
    public void task5(){
        Response response = given().get("http://3.86.189.171:1000/ords/hr/regions/3");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath = response.jsonPath();
        String region_name = jsonPath.getString("region_name");

        Assert.assertEquals(region_name,"Asia");

}

}
