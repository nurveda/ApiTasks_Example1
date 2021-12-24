package com.apiTasks.Nurr.tests;

/*
 * Given, accept type is json
 * When user sends get request to regions/3
 * Then response status code is must be 200
 * and body format is json
 * and response body contains Asia
 */

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class TC05 {

    @BeforeClass
    public void initalConnection(){
        baseURI= "http://54.166.122.207:1000/ords/hr";
    }


    @Test
    public void tc05(){
        Response response = when().get("/regions/3");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertEquals(response.jsonPath().getInt("region_id"), 3);
        assertEquals(response.jsonPath().getString("region_name"), "Asia");

        //assertion of links
        List<Map<String, Object>> insideLinks= jsonPath.getList("links");
        System.out.println("insideLinks = " + insideLinks);


        assertEquals(insideLinks.get(0).get("rel"),"self");
        System.out.println("insideLinks.get(0).get(\"rel\") = " + insideLinks.get(0).get("rel"));


        assertEquals(insideLinks.get(0).get("href"),"http://54.166.122.207:1000/ords/hr/regions/3");
        assertEquals(insideLinks.get(1).get("rel"),"edit");
        assertEquals(insideLinks.get(1).get("href"),"http://54.166.122.207:1000/ords/hr/regions/3");
        assertEquals(insideLinks.get(2).get("rel"),"describedby");
        assertEquals(insideLinks.get(2).get("href"),"http://54.166.122.207:1000/ords/hr/metadata-catalog/regions/item");
        assertEquals(insideLinks.get(3).get("rel"),"collection");
        assertEquals(insideLinks.get(3).get("href"),"http://54.166.122.207:1000/ords/hr/regions/");


        for(int i=0; i<=insideLinks.size()-1; i++) {
            if (insideLinks.get(i).containsKey("rel")) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        }


        for(int i=0; i<=insideLinks.size();i++){
            if(insideLinks.get(i).containsKey("href")){
                assertTrue(true);
            }else{
                assertTrue(false);
            }
        }
    }

}
