package com.leo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.leo.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Base class of all that is Integration Test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public abstract class BaseIT extends Assert {
    @LocalServerPort
    protected int port;

    @Before
    public void initialize() {
        RestAssured.port = port;
    }

    /**
     * Header for controller calls.
     */
    protected RequestSpecification requestSpecs() {
        return given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .log().everything();
    }
}
