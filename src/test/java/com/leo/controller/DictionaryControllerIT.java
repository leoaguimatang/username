package com.leo.controller;

import com.leo.BaseIT;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Integration tests for {@link DictionaryController}.
 */
public class DictionaryControllerIT extends BaseIT {

    private static final String DICTIONARY_URL = "/dictionary";

    @Test
    public void testGetWord() {

        requestSpecs()
            .param("search", "grass")
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("[0].id", equalTo(6))
            .log().everything(true)
            .get(DICTIONARY_URL);
    }

    @Test
    public void testPostWord() {

        String data = "{ \"word\": \"drugs\" }";

        requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("word", equalTo("drugs"))
            .log().everything(true)
            .post(DICTIONARY_URL);
    }

    @Test
    public void testPutWord() {

        String data = "{ \"word\": \"whare\" }";

        Long id = requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("word", equalTo("whare"))
            .log().everything(true)
            .post(DICTIONARY_URL)
                .jsonPath().getLong("id");

        data = "{ \"word\": \"whore\" }";

        requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("word", equalTo("whore"))
            .log().everything(true)
            .put(DICTIONARY_URL + "/" + id);
    }

    @Test
    public void testDeleteWord() {

        String data = "{ \"word\": \"crime\" }";

        Long id = requestSpecs()
            .body(data)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .body("word", equalTo("crime"))
            .log().everything(true)
            .post(DICTIONARY_URL)
                .jsonPath().getLong("id");

        requestSpecs()
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .log().everything(true)
            .delete(DICTIONARY_URL + "/" + id);
    }
}