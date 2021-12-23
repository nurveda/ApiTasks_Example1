package com.apiTasks.Nurr.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 TC01 --> spartanApi
 Given accept type is json
 And path param spartan id is 11 (do not use this num, use different number)
 When user sends a get request to /spartans/{id}
 Then status code is 200
 And content type is Json
 And
 "id": 11,
 "name": "Nona",
 "gender": "Female",
 "phone": 7959094216
 */


public class TC01 {

    @BeforeMethod
    public void setUp(){



    }


    @AfterMethod
    public void tearDown(){


    }



}
