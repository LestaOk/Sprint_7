package ru.yandex.praktikum.courierhelpers;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier() {
    }

    public Courier(String login) {
        this.login = login;
    }

    public Courier(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier randomCourier() {
        return new Courier(
                RandomStringUtils.randomAlphabetic(5, 10),
                RandomStringUtils.randomAlphabetic(5, 10),
                RandomStringUtils.randomAlphabetic(5, 10)
        );
    }

    public static Courier invalidCourierWithoutLogin() {
        return new Courier("password123", RandomStringUtils.randomAlphabetic(5, 10));
    }

    public static Courier invalidCourierWithLoginOnly() {
        return new Courier(RandomStringUtils.randomAlphabetic(5, 10));
    }
}
