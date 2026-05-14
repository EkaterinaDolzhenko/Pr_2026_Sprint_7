package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;

import java.net.HttpURLConnection;
import java.util.List;


public class OrderChecker {
    @Step("Успешное создание заказа")
    public int createdSuccessfully(ValidatableResponse createResponse) {
        Integer track = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");

        Assertions.assertNotNull(track, "Трек заказа не должен быть null");

        return track;
    }

    @Step("Успешное получение списка заказов")
    public List<Object> getSuccessfully(ValidatableResponse response) {
        List<Object> orders = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("orders");

        Assertions.assertNotNull(orders, "Список заказов не должен быть null");
        Assertions.assertFalse(orders.isEmpty(), "Список заказов не должен быть пустым");

        return orders;
    }
}
