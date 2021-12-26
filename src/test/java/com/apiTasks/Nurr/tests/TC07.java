package com.apiTasks.Nurr.tests;

import com.apiTasks.Nurr.tests.utilities.GlobalDataStore;
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
        spartanInfos.put("name","deneme1");
        spartanInfos.put("phone", 1212121212L);
        System.out.println("spartanInfos = " + spartanInfos); //i created my map then im gonna add it to my request

        //u should say which type u wanna get and which type u re sending to api
        Response postRequest = given().log().all()
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanInfos)
                .when().post("/api/spartans");
        postRequest.prettyPrint();


        GlobalDataStore.globalID = postRequest.path("data.id");

        postMsg= postRequest.path("success");

        assertEquals(postRequest.statusCode(),201);
        assertEquals(postRequest.contentType(),"application/json");
        assertEquals(postMsg,"A Spartan is Born!");

        //to verify ID is matching with created one u need to use get request
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", GlobalDataStore.globalID)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void updateSpartanWithPut(){

        Map<String, Object> spartanNewInfos= new HashMap<>();
        spartanNewInfos.put("gender","Male");
        spartanNewInfos.put("name","denemeBaşarılı");
        spartanNewInfos.put("phone",1212121212L);
        System.out.println("GlobalDataStore.globalID = " + GlobalDataStore.globalID); //it returns 0 WHY? i wanna make dynamic i made it static as well --> look for solutions later

        Response putRequest = given()
                .and().contentType(ContentType.JSON)
                .pathParam("id", 125)
                .and().body(spartanNewInfos)
                .when().put("/api/spartans/{id}");

        putRequest.prettyPrint();
        assertEquals(204,putRequest.statusCode());


        //USE DYNAMICALLY TRY TO SOLVE IT --> GIVES INTERNAL SERVER ERROR TRY TO FIX IT LATER
    }



    @Test
    public void updateWithPatch(){
        Map<String,Object> patchSpartan= new HashMap<>();
        patchSpartan.put("phone",3333333333L);

        Response patchResponse = given().contentType(ContentType.JSON)
                .and().pathParam("id", 125)
                .body(patchSpartan)
                .when().patch("/api/spartans/{id}");

        System.out.println("patchResponse.statusCode() = " + patchResponse.statusCode());
        assertEquals(204, patchResponse.statusCode());
    }


    @Test
    public void getTheSpartan(){

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",125)
                .when().get("api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json").log().all();
    }


    @Test
    public void deleteTheSpartan(){
        //u can delete 118,119,121,122,123,124
        given().pathParam("id",117)
                .and().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }



}
