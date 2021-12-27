package com.apiTasks.Nurr.tests;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/*
             given accept type is Json
             And path param postal-code is 42000
             when sends a get request to api.zippopotam.us/TR/{postal-code}
             Then status code should be 200
             and content type should be application/json
             and each "state" should be Konya
             and each "state abbreviation" should be 42
 */
public class TC10 {

    @BeforeClass
    public void initialConnection(){
        baseURI="http://api.zippopotam.us";
    }

    @Test
    public void tc10(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code", 42000)
                .when().get("/TR/{postal-code}");
        response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals(response.jsonPath().getString("\'post code\'"),"42000");
        Assert.assertEquals(response.jsonPath().getString("country"),"Turkey");
        Assert.assertEquals(response.jsonPath().getString("\'country abbreviation\'"),"TR");

        System.out.println(jsonPath.getList("places"));
        List<Map<String,String>> actualPlacesList= response.jsonPath().getList("places");

        for(int i=0; i<actualPlacesList.size(); i++){
            String actualState = jsonPath.getString("places.state["+i+"]");
            String actualNum = jsonPath.getString("places.\'state abbreviation\'["+i+"]");
//            System.out.println("actualNum = " + actualNum);
//            System.out.println("actualState = " + actualState);

            Assert.assertEquals(actualState,"Konya");
            Assert.assertEquals(actualNum,"42");
        }


    }


}
