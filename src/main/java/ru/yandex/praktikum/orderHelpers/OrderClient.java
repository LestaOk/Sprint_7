package ru.yandex.praktikum.orderHelpers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.Specification;

import java.util.Map;

import static ru.yandex.praktikum.constants.Config.ORDER_PATH;

public class OrderClient extends Specification {
    @Step("Creation of order")
    public ValidatableResponse orderCreation(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("Cancel order")
    public ValidatableResponse cancelOrder(int track) {
        return spec()
                .body(Map.of("track", track))
                .when()
                .put(ORDER_PATH + "/cancel")
                .then().log().all();
    }

    @Step("Get order list by parameters")
    public ValidatableResponse getOrdersByParameters(Map<String, String> parameters) {
        return spec()
                .queryParams(parameters)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }

    @Step("Get order list without parameters")
    public ValidatableResponse getOrders() {
        return spec()
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
}