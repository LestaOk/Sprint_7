package ru.yandex.praktikum.orderHelpers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertNotEquals;

public class OrderChecks {
    @Step("Check that order was created successfully")
    public int orderCreatedSuccessfully(ValidatableResponse createResponse) {
        int track = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");

        assertNotEquals(0, track);

        return track;
    }
}