package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.Client;
import praktikum.Credentials;

import java.net.HttpURLConnection;


public class CourierClient extends Client {

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post("/courier")
                .then().log().all();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse logIn(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post("/courier/login")
                .then().log().all();
    }

    @Step("Удаление курьера")
    public void deleteCourier(int id) {
        spec()
                .when()
                .delete("/courier/{id}", id)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK);      }
}
