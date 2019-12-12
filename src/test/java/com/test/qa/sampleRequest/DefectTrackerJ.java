package com.test.qa.sampleRequest;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DefectTrackerJ {

    @Test
    public void GET() {
        given().
                log().all().
                when().
                get("http://192.168.1.62:8081/defectservices/defectseverities").
                then().
                log().body();

    }

    @Test
    public void GET1(){
        given().
                when().
                get("http://192.168.1.62:8081/defectservices/defectseverities/").
                then().
                log().body().
                assertThat().
                body("name[20]",equalTo("thusy"));

    }

    @Test
    public void GET2(){
        given().
                when().
                get("http://192.168.1.62:8081/defectservices/defectseverities/").
                then().
                log().body().
                assertThat().
                body("name",hasItem("thusy"));

    }

    @Test
    public void GET3(){
        given().
                when().
                get("http://192.168.1.62:8081/defectservices/defectseverities/").
                then().
                log().body().
                assertThat().
                body("name",not(hasItem("jp")));
    }

    @Test
    public void GET4(){
        given().
                when().
                get("http://192.168.1.62:8081/defectservices/defectseverities/").
                then().
                log().body().
                assertThat().
                body("name",hasSize(21));
    }

}
