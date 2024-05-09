package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import ru.yandex.praktikum.courierhelpers.Courier;
import ru.yandex.praktikum.courierhelpers.CourierClient;
import ru.yandex.praktikum.orderhelpers.OrderChecks;
import ru.yandex.praktikum.orderhelpers.OrderClient;

import java.util.HashMap;

public class OrderListTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks check = new OrderChecks();
    private final CourierClient courierClient = new CourierClient();

    @DisplayName("Order list should be returned on get request")
    @Test
    public void getOrderList() {
        ValidatableResponse orders = orderClient.getOrders();
        check.orderListReturnedSuccessfully(orders);
    }


    @DisplayName("Empty order list should be returned for newly created courier")
    @Test
    public void getEmptyOrderListByCourierId() {
        var courier = Courier.randomCourier();
        int courierId = courierClient.createCourierGetId(courier);

        HashMap<String, String> listParameters = new HashMap<>();
        listParameters.put("courierId", String.valueOf(courierId));

        var orders = orderClient.getOrdersByParameters(listParameters);
        check.orderListIsEmpty(orders);
    }

    @DisplayName("Correct number of orders returns in case of using parameter limit")
    @Test
    public void getOrderListLimit5() {
        int limit = RandomUtils.nextInt(1, 10);
        HashMap<String, String> listParameters = new HashMap<>();
        listParameters.put("limit", String.valueOf(limit));

        var orders = orderClient.getOrdersByParameters(listParameters);
        check.orderListByLimit(orders, limit);
    }
}
