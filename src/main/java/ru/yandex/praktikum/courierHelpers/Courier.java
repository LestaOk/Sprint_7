package ru.yandex.praktikum.courierHelpers;

import org.apache.commons.lang3.RandomStringUtils;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Courier defaultCourier() {
        return new Courier(
                "login123",
                "password123",
                "name123"
        );
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
