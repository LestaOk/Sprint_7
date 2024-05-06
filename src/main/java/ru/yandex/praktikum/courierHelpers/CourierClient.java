package ru.yandex.praktikum.courierHelpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.Specification;
import java.util.Map;

import static ru.yandex.praktikum.constants.Config.COURIER_PATH;

public class CourierClient extends Specification {
    @Step("Login courier")
    public ValidatableResponse loginCourier(CourierCredentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Create courier")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Delete courier")
    public Response deleteCourier(int id) {
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_PATH + "/" + id);
    }
}
