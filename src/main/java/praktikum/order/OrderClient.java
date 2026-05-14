package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.Client;

public class OrderClient extends Client {

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post("/orders")
                .then().log().all();
    }

    @Step("Запрос списка заказов с количеством заказов")
    public ValidatableResponse createListOrder(int limit, int page) {
        return spec()
                .queryParam("limit", limit)
                .queryParam("page", page)
                .when()
                .get("/orders")
                .then().log().all();
    }
}
