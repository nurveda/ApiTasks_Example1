package com.apiTasks.Nurr.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/*
          given accept type is Json
          And path param "postal-code" is your own city (mine is bursa --> 16000, antalya --> 07000, izmir--> 3500, konya -->42000 ...)
          when sends a get request to api.zippopotam.us/TR/{postal-code}
          Then status code should be 200
          and "content type" should be application/json
          and "state" should be ur city
 */

public class TC09 {

    @BeforeClass
    public void initialConnection(){
        baseURI="http://api.zippopotam.us";
    }

    @Test
    public void tc09(){
        Response response = given().pathParam("postal-code", 16000)
                .and().pathParam("postal-code", 16000)
                .get("/TR/{postal-code}");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        String state= jsonPath.getString("places.state[0]");
        Assert.assertEquals(state,"Bursa");

    }

}
