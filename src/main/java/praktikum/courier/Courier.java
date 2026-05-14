package praktikum.courier;

import io.qameta.allure.Step;
import java.util.concurrent.ThreadLocalRandom;

public class Courier {

    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("Используем курьера с рандомным логином")
    public static Courier random(){
        var random = ThreadLocalRandom.current();
        String randomLogin = "tuna" + random.nextInt();
        return new Courier(randomLogin, "3322", "Fish");
    }

    @Step("Используем курьера без логина")
    public static Courier randomWithoutLogin(){
        return new Courier(null, "3322", "Fish");
    }

    @Step("Используем курьера с рандомным логином без пароля")
    public static Courier randomWithoutPassword(){
        var random = ThreadLocalRandom.current();
        String randomLogin = "tuna" + random.nextInt();
        return new Courier(randomLogin, null, "Fish");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
}
