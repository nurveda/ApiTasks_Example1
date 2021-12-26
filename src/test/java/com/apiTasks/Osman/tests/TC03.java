package com.apiTasks.Osman.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

/* TC03 --> spartanApi
        * Given accept type is Json
        * And ıd parameter value is 5 (do not use this num, use different number)
        * When user sends GET request to /api/spartans/{id}
        * Then response status code should be 200
        * And response content-type: application/json
        * And "Jocelin" should be in the payload --> means body(people likes to use fancy word)
 */

public class TC03 {

    @Test
    public void task3(){

        given().pathParam("id",80)
                .when().get("http://3.86.189.171:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json"))
                .and().body("name",equalTo("Jocelin"),"id",equalTo(80)
                ,"gender",equalTo("Female"),"phone",equalTo(4299058649L));



    }




}
