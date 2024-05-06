import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.courierHelpers.Courier;
import ru.yandex.praktikum.courierHelpers.CourierChecks;
import ru.yandex.praktikum.courierHelpers.CourierClient;
import ru.yandex.praktikum.courierHelpers.CourierCredentials;

public class CourierLoginTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    int courierId;

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            Response deleted = client.deleteCourier(courierId);
            check.deletedSuccessfully(deleted);
        }
    }

    @Test
    @DisplayName("Created courier should be able to log in")
    public void createdCourierLoggedInSuccessfully() {

        var courier = Courier.randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        var creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }
}
