package com.apiTasks.Osman.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
/*TC01 --> spartanApi
        Given accept type is json
        And path param spartan id is 32 (do not use this num, use different number)
        When user sends a get request to /spartans/{id}
        Then status code is 200
        And content type is Json
        And
       "id": 32,
       "name": "Tessie",
       "gender": "Female",
       "phone": 9545879168
        (use hamcrest matchers)

 */
public class TC01 {

    @Test
    public void task01(){

            given().accept(ContentType.JSON)
                    .when().log().all().pathParam("id",32)
                    .when().get("http://3.86.189.171:8000/api/spartans/{id}")
                    .then().assertThat().statusCode(200)
                    .and().contentType("application/json").
                    and().body("id",equalTo("32"),
                            "name",equalTo("Tessie"),
                            "gender",equalTo("Female"),
                            "phone",equalTo(9545879168l));




    }


}
