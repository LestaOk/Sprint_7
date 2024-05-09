package ru.yandex.praktikum.orderhelpers;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Data
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Order(List<String> color) {
        this.firstName = RandomStringUtils.randomAlphabetic(5, 8);
        this.lastName = RandomStringUtils.randomAlphabetic(5, 10);
        this.address = RandomStringUtils.randomAlphanumeric(25);
        this.metroStation = RandomStringUtils.randomAlphabetic(5, 10);
        this.phone = "+7" + RandomStringUtils.randomNumeric(10);
        this.rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(2));
        this.deliveryDate = "2024-05-15";
        this.comment = RandomStringUtils.randomAlphabetic(0, 30);
        this.color = color;
    }
}