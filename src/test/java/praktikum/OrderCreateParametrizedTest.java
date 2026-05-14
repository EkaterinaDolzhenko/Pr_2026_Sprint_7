package praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.order.Order;
import praktikum.order.OrderChecker;
import praktikum.order.OrderClient;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderCreateParametrizedTest {

    private final OrderClient orderClient = new OrderClient();
    private final OrderChecker check = new OrderChecker();

    static Stream<Arguments> orderColorsProvider() {
        return Stream.of(
                Arguments.of(Order.orderWithoutColor(), "Без цвета"),
                Arguments.of(Order.orderWithBlackColor(), "Только BLACK"),
                Arguments.of(Order.orderWithGreyColor(), "Только GREY"),
                Arguments.of(Order.orderWithBothColors(), "BLACK и GREY")
        );
    }

    @DisplayName("Создание заказа с разными комбинациями цветов")
    @ParameterizedTest(name = "Тест {index}: {1}")
    @MethodSource("orderColorsProvider")
    public void createOrderWithDifferentColorsTest(Order order, String testName) {
        ValidatableResponse createResponse = orderClient.createOrder(order);

        int track = check.createdSuccessfully(createResponse);

        assertNotEquals(0, track, "Трек заказа не должен быть 0");
    }
}