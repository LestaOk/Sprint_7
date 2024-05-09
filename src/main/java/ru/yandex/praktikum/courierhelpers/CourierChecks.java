package ru.yandex.praktikum.courierhelpers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;
import static ru.yandex.praktikum.constants.ValidationMessages.*;

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
    public void deletedSuccessfully(ValidatableResponse deleteResponse) {
        boolean deleted = deleteResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("ok");

        assertTrue(deleted);
    }

    @Step("Check that courier creation with existing login returns error")
    public void conflictOnCreation(ValidatableResponse createResponse) {
        String responseMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .extract()
                .path("message");

        assertEquals(CREATION_CONFLICT, responseMessage);
    }

    @Step("Check that courier creation without all mandatory fields returns error")
    public void missingDataOnCreation(ValidatableResponse createResponse) {
        String responseMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals(MISSING_DATA_ON_CREATION, responseMessage);
    }

    @Step("Check that courier login without all mandatory parameters returns error")
    public void missingDataOnLogin(ValidatableResponse createResponse) {
        String responseMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals(MISSING_DATA_ON_LOGIN, responseMessage);
    }

    @Step("Check that courier login with incorrect credentials returns error")
    public void incorrectDataOnLogin(ValidatableResponse createResponse) {
        String responseMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .extract()
                .path("message");

        assertEquals(ACCOUNT_NOT_FOUND, responseMessage);
    }
}
