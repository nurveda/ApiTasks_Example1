package com.apiTasks.Nurr.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class TC07 {

    static  int postID;
    String postMsg;

    @BeforeClass
    public void tc07(){
        baseURI= "http://54.166.122.207:8000";
    }

    @Test
    public void createAnSpartanWithPostReq(){
        //first i need to check documentation how should create a spartan -->
        // {
        //  "gender": "string",
        //  "name": "string",
        //  "phone": 0
        //}
        //i need to add this--> the easiest way to do it create an empty map and put that infos

        Map<String, Object> spartanInfos= new LinkedHashMap<>();  //we can use HashMap as well
        spartanInfos.put("gender","Male");
        spartanInfos.put("name","CengizAtay");
        spartanInfos.put("phone", 1212121212L);
        System.out.println("spartanInfos = " + spartanInfos); //i created my map then im gonna add it to my request

        //u should say which type u wanna get and which type u re sending to api
        Response postRequest = given().log().all()
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanInfos)
                .when().post("/api/spartans");
        postRequest.prettyPrint();


        postID= postRequest.path("data.id");

        postMsg= postRequest.path("success");

        assertEquals(postRequest.statusCode(),201);
        assertEquals(postRequest.contentType(),"application/json");
        assertEquals(postMsg,"A Spartan is Born!");

        //to verify ID is matching with created one u need to use get request
        given().accept(ContentType.JSON)
                .and().pathParam("id",postID)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200).log().all();
    }


    @Test
    public void updateSpartanWithPut(){

        Map<String, Object> spartanNewInfos= new HashMap<>();
        spartanNewInfos.put("gender","Female");
        spartanNewInfos.put("name","Eysann");
        spartanNewInfos.put("phone",2222222222L);
        System.out.println("postID = " + postID); //it returns 0 WHY? i made it static as well --> look for solutions

        given().accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON)
                .pathParam("id",120)
                .and().body(spartanNewInfos)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204).log().all();



    }
}
