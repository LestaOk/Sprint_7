package ru.yandex.praktikum.courierhelpers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.Specification;
import java.util.Map;

import static ru.yandex.praktikum.constants.Config.COURIER_PATH;

public class CourierClient extends Specification {
    private final CourierChecks check = new CourierChecks();

    @Step("Login courier")
    public ValidatableResponse loginCourier(CourierCredentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Login courier with login only")
    public ValidatableResponse loginCourierOnlyWithLogin(String courierLogin) {
        return spec()
                .body(Map.of("login", courierLogin))
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Login courier with password only")
    public ValidatableResponse loginCourierOnlyWithPassword(String courierPassword) {
        return spec()
                .body(Map.of("password", courierPassword))
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Login courier with invalid password")
    public ValidatableResponse loginCourierWithInvalidCreds(CourierCredentials creds) {
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
    public ValidatableResponse deleteCourier(int id) {
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all();
    }

    @Step("Create courier and get id")
    public int createCourierGetId(Courier courier) {
        createCourier(courier);
        var creds = CourierCredentials.from(courier);

        return check.loggedInSuccessfully(loginCourier(creds));
    }
}
