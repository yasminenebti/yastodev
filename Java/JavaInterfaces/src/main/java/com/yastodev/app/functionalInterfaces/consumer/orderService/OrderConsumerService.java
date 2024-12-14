package com.yastodev.app.functionalInterfaces.consumer.orderService;

import java.util.List;
import java.util.function.Consumer;

public class OrderConsumerService {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, "John", 100.0),
                new Order(2, "Elsa", 200.0),
                new Order(3, "Gavin", 300.0)
        );

        Consumer<Order> logCustomerName = order -> System.out.println("Customer Name: " + order.getCustomerName());
        Consumer<Order> logTotalAmount = order -> System.out.println("Total Amount: " + order.getTotalAmount());
        Consumer<Order> logSeparator = order -> System.out.println("-------");


        processOrders(orders, logCustomerName.andThen(logTotalAmount).andThen(logSeparator));
    }

    public static void processOrders(List<Order> orders, Consumer<Order> consumer) {
        for (Order order : orders) {
            consumer.accept(order);
        }
    }
}
