package com.test.qa.sampleRequest;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;

public class Designation {

    @Test
    public void GET(){
        given().
                when().
                get("http://192.168.1.62:8040/leave_system/designation").
                then().
                log().body().
                assertThat().
                body("designation",hasItem("priya"));
    }

    @Test
    public void POST(){
        given().
                contentType(ContentType.JSON).
                when().
                log().all().
                body("{\n" +
                        "       \n" +
                        "        \"designation\": \"engineerQA\"\n" +
                        "    }").
                post("http://192.168.1.62:8040/leave_system/designation").
                then().
                log().body().
                assertThat().
                statusCode(201);

    }
    @Test
    public void PUT(){
        given().
                contentType(ContentType.JSON).
                when().
                log().all().
                body("{\n" +
                        "    \"designation\": \"SoftwareQA\"\n" +
                        "}").
                put("http://192.168.1.62:8040/leave_system/designation/29").
                then().
                log().body().assertThat().statusCode(200);

    }

    @Test
    public void DELETE(){
        given().
                contentType(ContentType.JSON).
                when().
                log().all().
                delete("http://192.168.1.62:8040/leave_system/designation/32").
                then().
                log().body().
                assertThat().
                statusCode(200);
    }
    @DataProvider
    public static Object[][] URLgetReplace() {
        return new Object[][]{
                {"designation"},
                {"recuitmentType"},
                {"role"},
        };


    }

    @Test(dataProvider = "URLgetReplace")
    public void GETdataProvider(String getURL){
        given().
                pathParam("getURL",getURL).
                log().all().
                when().
                get("http://192.168.1.62:8040/leave_system/{getURL}").
                then().
                log().body();

    }

    @DataProvider(name = "add")
    public static Object [][] add(){
        return new Object[][]{
                {"ENGINEER"},
                {"ENGINEER1"}
        };
    }

    @Test(dataProvider="add")
    public void createDesignationTest(String designation){
        given().
                contentType(ContentType.JSON).
                log().all().
                when().

                body("{\n" +
                        "        \n" +
                        "        \"designation\": \""+designation+"\"\n" +
                        "    }").
                post("http://192.168.1.62:8040/leave_system/designation").
                then().
                assertThat().
                statusCode(201);
    }
}
