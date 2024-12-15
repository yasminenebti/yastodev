package com.yastodev.app.functionalInterfaces.predicate.orderService;

import com.yastodev.app.functionalInterfaces.utils.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrderPredicateService {
    public static void main(String[] args) {
//        List<Order> orders = List.of(
//                new Order(1, "John", 100.0),
//                new Order(2, "Elsa", 200.0),
//                new Order(3, "Gavin", 300.0)
//        );

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "Alice", 250.0));
        orders.add(new Order(2, "John", 100.5));
        orders.add(new Order(3, "Charlie", 300.75));

        Predicate<Order> isHighValueOrder = order -> order.getTotalAmount() > 200.0;
        Predicate<Order> isJohnOrder = order -> order.getCustomerName().equals("John");

        displayOrders(orders, isHighValueOrder);

        System.out.println("Fetching John's orders");
        displayOrders(orders, isJohnOrder);

        System.out.println("-----------");

        // filtering orders
        orders.stream()
                .filter(isHighValueOrder)
                .forEach(order -> System.out.println("Order: " + order.getOrderId() + " of customer " + order.getCustomerName()));
    }

    public static void displayOrders(List<Order> orders, Predicate<Order> predicate) {
        for (Order order : orders) {
            if (predicate.test(order)) {
                System.out.println("Order: " + order.getOrderId() + " of customer " + order.getCustomerName());
            }
        }
    }
}
