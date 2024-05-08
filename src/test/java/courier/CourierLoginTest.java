package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.courierHelpers.Courier;
import ru.yandex.praktikum.courierHelpers.CourierChecks;
import ru.yandex.praktikum.courierHelpers.CourierClient;
import ru.yandex.praktikum.courierHelpers.CourierCredentials;

public class CourierLoginTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private Courier courier = new Courier();
    int courierId;

    @Before
    @DisplayName("Create courier for the test")
    public void createCourier() {
        courier = Courier.randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
    }

    @After
    @DisplayName("Delete courier created for the test")
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse deleted = client.deleteCourier(courierId);
            check.deletedSuccessfully(deleted);
        }
    }

    @Test
    @DisplayName("Created courier should be able to log in")
    public void createdCourierLoggedInSuccessfully() {
        var creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    /* Тест ломает сервис - при попытке залогиниться без указания пароля возвращается Service unavailable
    * */
    @Test
    @DisplayName("Courier couldn't log in without password provided")
    public void courierLoginWithoutProvidedPasswordReturnsError() {
        var login = courier.getLogin();
        ValidatableResponse loginResponse = client.loginCourierOnlyWithLogin(login);
        check.missingDataOnLogin(loginResponse);
    }

    @Test
    @DisplayName("Courier couldn't log in without login provided")
    public void courierLoginWithoutProvidedLoginReturnsError() {
        var password = courier.getPassword();
        ValidatableResponse loginResponse = client.loginCourierOnlyWithPassword(password);
        check.missingDataOnLogin(loginResponse);
    }

    @Test
    @DisplayName("Courier is not able to log in with invalid password provided")
    public void courierLoginWithIvalidPasswordReturnsError() {
        var creds = CourierCredentials.from(courier);
        creds.setPassword("new_password");

        ValidatableResponse loginResponse = client.loginCourierWithInvalidCreds(creds);
        check.incorrectDataOnLogin(loginResponse);
    }

    @Test
    @DisplayName("Courier is not able to log in with invalid login provided")
    public void courierLoginWithIvalidLoginReturnsError() {
        var creds = CourierCredentials.from(courier);
        creds.setLogin("new_login");

        ValidatableResponse loginResponse = client.loginCourierWithInvalidCreds(creds);
        check.incorrectDataOnLogin(loginResponse);
    }
}
