package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.courier.Courier;
import praktikum.courier.CourierChecker;
import praktikum.courier.CourierClient;

public class CourierLoginTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecker check = new CourierChecker();
    private int courierId;
    private Courier courier;

    @BeforeEach
    public void setUp() {
        courier = Courier.random();
        ValidatableResponse createResponse = client.create(courier);
        check.createdSuccessfully(createResponse);
    }

    @AfterEach
    public void dropCourier() {
        // Для получения ID в негативных тестах
        if (courierId == 0) {
            var creds = Credentials.fromCourier(courier);
            ValidatableResponse loginResponse = client.logIn(creds);
            courierId = check.loggedInSuccessfully(loginResponse);
        }
        if (courierId != 0) {
            client.deleteCourier(courierId);
            courierId = 0;
        }
    }

    @DisplayName("Успешный вход в систему")
    @Test
    public void courierSuccessLogin() {
        var creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

    }

    @DisplayName("Вход в систему без логина")
    @Test
    public void courierLoginWithoutLoginFailed() {
        var creds = Credentials.withEmptyLogin(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loggedInBadRequest(loginResponse);
    }

    @DisplayName("Вход в систему без пароля")
    @Test
    public void courierLoginWithoutPasswordFailed() {
        var creds = Credentials.withEmptyPassword(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loggedInBadRequest(loginResponse);
    }

    @DisplayName("Вход в систему с ошибкой в логине")
    @Test
    public void courierLoginWithWrongLoginFailed() {
       var creds = Credentials.withWrongLogin(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loggedInNotFound(loginResponse);
    }

    @DisplayName("Вход в систему с ошибкой в пароле")
    @Test
    public void courierLoginWithWrongPasswordFailed() {
        var creds = Credentials.withWrongPassword(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        check.loggedInNotFound(loginResponse);
    }
}