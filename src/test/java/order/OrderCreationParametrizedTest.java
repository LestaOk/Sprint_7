package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.orderHelpers.Order;
import ru.yandex.praktikum.orderHelpers.OrderChecks;
import ru.yandex.praktikum.orderHelpers.OrderClient;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreationParametrizedTest {
    private final List<String> color;
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();
    private int track;
    public OrderCreationParametrizedTest(List<String> color) {
        this.color = color;
    }

    @After
    public void cleanUp() {
        client.cancelOrder(track);
    }

    @Parameterized.Parameters(name = "{index}: Color={0}")
    public static Object[][] chooseColorOptions() {
        return new Object[][]{
                {List.of("GREY")},
                {List.of("BLACK")},
                {List.of("BLACK", "GREY")},
                {List.of()}
        };
    }

    @DisplayName("Create order")
    @Test
    public void createNewOrderTest() {
        Order order = new Order(color);
        ValidatableResponse response = client.orderCreation(order);
        track = check.orderCreatedSuccessfully(response);
        System.out.println(track);
    }
}
