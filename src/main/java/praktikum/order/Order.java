package praktikum.order;

import io.qameta.allure.Step;

import java.util.List;

public class Order {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public Order(String firstName, String lastName, String address, int metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Step("Заполнение базового заказ без указания цвета")
    public static Order orderWithoutColor() {
        return new Order(
                "Лариса",
                "Долина",
                "Тверская 11",
                209,
                "89054678998",
                1,
                "2026-05-15",
                "",
                null
        );
    }

    @Step("Заполнение заказ с цветом BLACK")
    public static Order orderWithBlackColor() {
        return new Order(
                "Лариса",
                "Долина",
                "Тверская 11",
                209,
                "89054678998",
                1,
                "2026-05-15",
                "",
                List.of("BLACK")
        );
    }

    @Step("Заполнение заказ с цветом GREY")
    public static Order orderWithGreyColor() {
        return new Order(
                "Лариса",
                "Долина",
                "Тверская 11",
                209,
                "89054678998",
                1,
                "2026-05-15",
                "",
                List.of("GREY")
        );
    }

    @Step("Заполнение заказ с обоими цветами")
    public static Order orderWithBothColors() {
        return new Order(
                "Лариса",
                "Долина",
                "Тверская 11",
                209,
                "89054678998",
                1,
                "2026-05-15",
                "",
                List.of("BLACK", "GREY")
        );
    }


    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public int getMetroStation() { return metroStation; }
    public String getPhone() { return phone; }
    public int getRentTime() { return rentTime; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getComment() { return comment; }
    public List<String> getColor() { return color; }
}