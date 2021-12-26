package com.apiTasks.Osman.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

public class TC07 {
/*TC07 --> spartanApi
      Creat spartan flow like:
          @BeforeClass
          public void beforeclass(){
              baseURI= ConfigurationReader.get("spartan_api_url");
          }
          @Test(priority = 1)
          public void POSTNewSpartan(){}

          @Test(priority = 2)
          public void PUTExistingSpartan(){}

          @Test
          public void PATCHExistingSpartan(){}

          @Test
          public void GETThatSpartan(){}

          @Test
          public void DELETEThatSpartan(){}
      }
 */

    int id ;

    @BeforeClass
    public void beforeclass(){baseURI= "http://3.86.189.171:8000/";}

    @Test(priority = 1)
    public void POSTNewSpartan(){

        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("name","John Snow");
        requestMap.put("gender","Male");
        requestMap.put("phone",5251234545L);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .and()
                .post("api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success",is("A Spartan is Born!"),
                        "data.name",equalTo("John Snow"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(5251234545L));


        ;


    }

    @Test(priority = 2)
    public void PUTExistingSpartan(){

        Map<String,Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name","John Snow2") ;
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",5251234545L);

        given().log().all()
                .and()
                .contentType("application/json")
                .and()
                .pathParam("id",104)
                .body(putRequestMap)
                .when().put("api/spartans/{id}")
                .then().log().all()
                .statusCode(204);

    }

    @Test
    public void PATCHExistingSpartan(){

        Map<String, Object> patchMap = new HashMap<>();

        patchMap.put("name","John Snow3");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",104)
                .and()
                .body(patchMap)
                .patch("/api/spartans/{id}")
                .then().statusCode(204);


    }

    @Test
    public void GETThatSpartan(){

        given().pathParam("id",104)
                .and().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .and().body("name",equalTo("John Snow3"),
                        "gender",equalTo("Male"),
                        "phone",equalTo(5251234545L));


    }

    @Test
    public void DELETEThatSpartan(){

        given().pathParam("id",104)
                .and()
                .delete("/api/spartans/{id}")
                .then().assertThat()
                .statusCode(204).log().all();


    }

}
