package com.test.qa.sampleRequest;

import com.test.qa.utils.APIUtils;
import com.test.qa.utils.Constants;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class DefectTracker {

    @Test
    public void apiGet(){
        SoftAssert softAssert = new SoftAssert();
        Response response = get("http://192.168.1.62:8081/defectservices/defectseverities");
        softAssert.assertEquals(response.getStatusCode(),200,"Invalid Code");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        System.out.println(response.time());
        softAssert.assertAll();
    }

    @Test
    public void apiPost(){
        SoftAssert softAssert = new SoftAssert();
        String body = APIUtils.readBody(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"requestbody"+File.separator+"defectTracker.json");
        Response response = given().header("Content-Type","application/json").body(body).when().post("http://192.168.1.62:8081/defectservices/defectseverity");
        softAssert.assertEquals(response.getStatusCode(),200,"invalid code");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        System.out.println(response.time());
        softAssert.assertAll();
    }

    @Test
    public void checkPostData(){
        SoftAssert softAssert = new SoftAssert();
        Response response = given().header("Content-Type","application/json").when().get("http://192.168.1.62:8081/defectservices/defectseverity/"+"93");
        softAssert.assertEquals(response.getStatusCode(),200,"invalid code");
        softAssert.assertEquals(APIUtils.extractResponse(response,"name"),"thusy","invalid message");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        System.out.println(response.time());
        softAssert.assertAll();
    }

}
