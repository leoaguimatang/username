package com.leo.controller;

import com.leo.BaseIT;
import com.leo.exception.UsernameException;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Integration tests for {@link UserController}.
 */
public class UserControllerIT extends BaseIT {

    private static final String USER_URL = "/users";

    @Test
    public void testGetUser() {
        requestSpecs()
            .param("search", "matthew")
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("[0].id", equalTo(7))
            .log().everything(true)
            .get(USER_URL);
    }

    @Test
    public void testCheckUsername() {
        assertTrue(requestSpecs()
            .param("username", "marlon")
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .log().everything(true)
            .get(USER_URL + "/checkUsername")
                .jsonPath().getBoolean("valid"));
    }

    @Test
    public void testPostUser() {
        String data = "{ \"username\": \"matthew\" }";

        requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .log().everything(true)
            .post(USER_URL);
    }

    @Test
    public void testPostBadUsername() {
        String data = "{ \"username\": \"cannabisgalore\" }";

        requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .log().everything(true)
            .post(USER_URL);
    }

    @Test
    public void testPostVeryBadUsername() {
        String data = "{ \"username\": \"cannabis\" }";

        requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .log().everything(true)
            .post(USER_URL);
    }
}