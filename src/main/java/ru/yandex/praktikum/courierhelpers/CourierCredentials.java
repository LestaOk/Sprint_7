package ru.yandex.praktikum.courierhelpers;

import lombok.Data;

@Data
public class CourierCredentials {
    private String login;
    private String password;
    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials() {
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }
}
