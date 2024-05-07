import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.courierHelpers.Courier;
import ru.yandex.praktikum.courierHelpers.CourierChecks;
import ru.yandex.praktikum.courierHelpers.CourierClient;

public class CourierCreationTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    int courierId;

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse deleted = client.deleteCourier(courierId);
            check.deletedSuccessfully(deleted);
        }
    }

    @Test
    @DisplayName("Courier successful creation")
    public void courierCreationWithValidFieldsReturnsSuccess() {

        var courier = Courier.randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
    }

    @Test
    @DisplayName("Courier creation with existing login is prohibited")
    public void courierCreationWithExistingLoginNotPossible() {
        var courier = Courier.randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        createResponse = client.createCourier(courier);
        check.conflictOnCreation(createResponse);
    }

    @Test
    @DisplayName("Courier creation without provided login is not possible")
    public void courierCreationWithoutProvidedLoginReturnsError() {
        var courier = Courier.invalidCourierWithoutLogin();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.missingDataOnCreation(createResponse);
    }

    @Test
    @DisplayName("Courier creation without provided password is not possible")
    public void courierCreationWithoutProvidedPasswordReturnsError() {
        var courier = Courier.invalidCourierWithLoginOnly();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.missingDataOnCreation(createResponse);
    }
}
