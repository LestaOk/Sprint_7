package ru.yandex.praktikum.courierHelpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;
import static ru.yandex.praktikum.constants.ValidationMessages.CREATION_CONFLICT;
import static ru.yandex.praktikum.constants.ValidationMessages.MISSING_DATA;

public class CourierChecks {
    @Step("Check that courier logged in successfully")
    public int loggedInSuccessfully(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");

        assertNotEquals(0, id);

        return id;
    }

    @Step("Check that courier was created successfully")
    public void createdSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");

        assertTrue(created);
    }

    @Step("Check that courier was deleted successfully")
    public void deletedSuccessfully(Response deleteResponse) {
        boolean deleted = deleteResponse
                .then()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("ok");

        assertTrue(deleted);
    }

    @Step("Check that courier creation with existing login returns error")
    public void conflictOnCreation(ValidatableResponse createResponse) {
        String conflicted = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .extract()
                .path("message");

        assertEquals(CREATION_CONFLICT, conflicted);
    }

    public void missingData(ValidatableResponse createResponse) {
        String missingData = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals(MISSING_DATA, missingData);
    }
}
