package com.pet.project;

import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class ApiTest extends BaseTest {

    @Test
    public void deleteRecord() {

        RestAssured.given()
                .baseUri(testConfig.baseUrl())
                .basePath("/api/task/all")
                .delete()
                .then().statusCode(200);
    }

}
