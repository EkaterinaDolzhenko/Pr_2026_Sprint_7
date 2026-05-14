package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.courier.Courier;
import praktikum.courier.CourierChecker;
import praktikum.courier.CourierClient;


public class CourierCreateTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecker check = new CourierChecker();
    private int courierId;

    @AfterEach
    public void dropCourier() {
        if (courierId != 0) {
            client.deleteCourier(courierId);
        }
    }

    @DisplayName("Успешное создание курьера")
    @Test
    public void courierSuccessCreation() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.create(courier);
        check.createdSuccessfully(createResponse);

        var creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    @DisplayName("Неуспешное создание одинаковых курьеров")
    @Test
    public void courierSameLoginFailed() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.create(courier);
        check.createdSuccessfully(createResponse);

        ValidatableResponse createResponse2 = client.create(courier);
        check.createdConflict(createResponse2);

        var creds = Credentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    @DisplayName("Неуспешное создание курьера без логина")
    @Test
    public void courierWithoutLoginFailed() {
        var courier = Courier.randomWithoutLogin();
        ValidatableResponse createResponse = client.create(courier);
        check.createdBadRequest(createResponse);
    }

    @DisplayName("Неуспешное создание курьера без пароля")
    @Test
    public void courierWithoutPasswordFailed() {
        var courier = Courier.randomWithoutPassword();
        ValidatableResponse createResponse = client.create(courier);
        check.createdBadRequest(createResponse);
    }
}
