package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.order.OrderChecker;
import praktikum.order.OrderClient;


public class OrderListTest {
    private final OrderClient list = new OrderClient();
    private final OrderChecker check = new OrderChecker();

    @DisplayName("Получение списка 10 доступных для взятия заказов")
    @Test
    public void orderListTest() {
        ValidatableResponse createResponse = list.createListOrder(10, 0);
        check.getSuccessfully(createResponse);
    }
}
