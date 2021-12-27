package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



/*
     given accept type is Json
     And path param postal-code is your own city (mine is bursa --> 16000, antalya --> 07000, izmir--> 3500, konya -->42000 ...)
     when sends a get request to api.zippopotam.us/TR/{postal-code}
     Then status code should be 200
     and content type should be application/json
     and "Date", "Connection","Server","Content-Encoding" headers needs to be exist in response headers

 */

public class TC08 {


    @BeforeClass
    public void initialConnection(){
        baseURI="http://api.zippopotam.us";
    }

    @Test
    public void tc08(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code", 16000)
                .get("/TR/{postal-code}");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals(jsonPath.getString("\'post code\'"), "16000");
        Assert.assertEquals(jsonPath.getString("\'country\'"), "Turkey");
        Assert.assertEquals(jsonPath.getString("\'country abbreviation\'"), "TR");


        Map<String,Object> expectedMap= new LinkedHashMap<>();
        expectedMap.put("place name","");
        expectedMap.put("longitude","33.5064");
        expectedMap.put("state", "Bursa");
        expectedMap.put("state abbreviation", "16");
        expectedMap.put("latitude","39.8453");

        System.out.println("actual= " + jsonPath.getList("places").get(0));
        System.out.println("expected = " + expectedMap);

        Assert.assertEquals(jsonPath.getList("places").get(0),expectedMap);
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Connection"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Server"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Content-Encoding"));
    }








}
