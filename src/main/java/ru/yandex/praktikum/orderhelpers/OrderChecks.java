package ru.yandex.praktikum.orderhelpers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.*;
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

    @Step("Check that order list was returned")
    public void orderListReturnedSuccessfully(ValidatableResponse orders) {
        orders.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders", notNullValue());
    }

    @Step("Check that no orders were returned")
    public void orderListIsEmpty(ValidatableResponse orders) {
        orders.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders.size()", equalTo(0));
    }

    @Step("Check that orders list size is equal to the specified limit")
    public void orderListByLimit(ValidatableResponse orders, int limit) {
        orders.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders.size()", equalTo(limit));
    }
}