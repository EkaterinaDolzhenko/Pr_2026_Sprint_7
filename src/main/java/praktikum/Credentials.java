package praktikum;


import io.qameta.allure.Step;
import praktikum.courier.Courier;

public class Credentials {
    private final String login;
    private final String password;

    public Credentials(String login, String password) {

        this.login = login;
        this.password = password;
    }

    @Step("Используем данные курьера")
    public static Credentials fromCourier(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    @Step("Используем пароль курьера, логин пустой")
    public static Credentials withEmptyLogin(Courier courier) {
        return new Credentials("", courier.getPassword());
    }

    @Step("Используем логин  курьера, пароль пустой")
    public static Credentials withEmptyPassword(Courier courier) {
        return new Credentials(courier.getLogin(),"");
    }

    @Step("Используем данные курьера, ошибка в логине")
    public static Credentials withWrongLogin(Courier courier) {
        return new Credentials("salmon3323" ,courier.getPassword());
    }

    @Step("Используем данные курьера, ошибка в пароле")
    public static Credentials withWrongPassword(Courier courier) {
        return new Credentials(courier.getLogin(),"3323");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
