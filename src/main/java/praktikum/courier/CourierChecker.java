package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CourierChecker {
    @Step("Успешное создание курьера")
    public void createdSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok")
                ;
        Assertions.assertTrue(created);
    }

    @Step("Неуспешное создание курьера с тем же логином")
    public void createdConflict(ValidatableResponse createResponse) {
        String actualMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .extract()
                .path("message");

        Assertions.assertEquals("Этот логин уже используется. Попробуйте другой.", actualMessage);
    }

    @Step("Неуспешное создание курьера без обязательного поля")
    public void createdBadRequest(ValidatableResponse createResponse) {
        String actualMessage = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        Assertions.assertEquals("Недостаточно данных для создания учетной записи", actualMessage);
    }

    @Step("Успешный вход в систему")
    public Integer loggedInSuccessfully(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
        assertNotEquals(0, id);
        return id;
    }

    @Step("Ошибка входа в систему без обязательных полей")
    public void loggedInBadRequest(ValidatableResponse loginResponse) {
        String actualMessage = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        Assertions.assertEquals("Недостаточно данных для входа", actualMessage);
    }

    @Step("Ошибка входа в систему с несуществующей парой логин-пароль")
    public void loggedInNotFound(ValidatableResponse loginResponse) {
        String actualMessage = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .extract()
                .path("message");

        Assertions.assertEquals("Учетная запись не найдена", actualMessage);
    }
}
