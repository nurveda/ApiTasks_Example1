package com.apiTasks.Osman.tests;

import com.sun.org.apache.xpath.internal.operations.And;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;


public class TC04 {
   /* TC04 --> spartanApi
           * When user sends a get request to api/spartans/7
            * Then status code should be 200
            * And content type should be application/json;
            * And name should contain Fidole

            ( u can try this data as well...
                    id   name    phone       gender
                    7	Hershel 5278678322	Male
                    12	Sol     7006438852	Male
                    29	Dorelle	6687391932	Female
                    67	Janette	9887020445	Female
            )

    */

    @Test
    public void task4(){

        given().pathParam("id",7)
        .get("http://3.86.189.171:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("name",equalTo("Hershel")
                        ,"phone",equalTo(5278678322L)
                        ,"gender",equalTo("Male"));





    }

}
