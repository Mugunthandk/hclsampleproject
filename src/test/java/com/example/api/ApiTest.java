
package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test(priority = 1)
    public void verifyGetApi() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .header("Content-Type", "application/json")

        .when()
            .get("/products/1")

        .then()
            .statusCode(404);

        System.out.println("GET API Test Completed");
    }

    @Test(priority = 2)
    public void verifyGetUserApi() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()

        .when()
            .get("/users/1")

        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue());

        System.out.println("GET User API Passed");
    }

    @Test(priority = 3)
    public void verifyPostApi() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody =
                "{"
                + "\"title\":\"Test Product\","
                + "\"body\":\"E-Commerce API Testing\","
                + "\"userId\":1"
                + "}";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)

        .when()
            .post("/posts")

        .then()
            .statusCode(201)
            .body("title", equalTo("Test Product"));

        System.out.println("POST API Passed");
    }

    @Test(priority = 4)
    public void verifyPutApi() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody =
                "{"
                + "\"id\":1,"
                + "\"title\":\"Updated Product\","
                + "\"body\":\"Updated API Data\","
                + "\"userId\":1"
                + "}";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)

        .when()
            .put("/posts/1")

        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Product"));

        System.out.println("PUT API Passed");
    }

    @Test(priority = 5)
    public void verifyDeleteApi() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()

        .when()
            .delete("/posts/1")

        .then()
            .statusCode(200);

        System.out.println("DELETE API Passed");
    }
}
