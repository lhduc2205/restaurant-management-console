package utils;

import models.dtos.MenuDto;
import models.dtos.MenuItemDto;
import models.dtos.OrderDetailDto;
import models.dtos.OrderDto;

import java.util.List;

public class OrderDisplayUtil {
    public static void displayOrder(List<OrderDto> orders) {
        if (orders.isEmpty()) {
            System.out.println("- There's no order here, let create them! -");
            return;
        }
        for (OrderDto order : orders) {
            displayOrder(order);
            System.out.println("-------------------------------------");
        }
    }

    public static void displayOrder(OrderDto order) {
        System.out.println("\nOrder " + (order.getId())  + "-------------------");
        System.out.println("+ Quantity: " + order.getQuantity());
        System.out.println("+ Total price: " + order.getTotalPrice());
        System.out.println("+");
        displayOrderDetail(order.getOrderDetail());
    }

    public static void displayOrderDetail(List<OrderDetailDto> orderDetails) {
        if (orderDetails.isEmpty()) {
            System.out.println("+\tOrders detail are empty.");
            return;
        }

        for (int i = 0; i < orderDetails.size(); i++) {
            displayOrderDetail(i + 1, orderDetails.get(i));
            if (i < orderDetails.size() - 1) {
                System.out.println("+\t-------------");
            }
        }
    }
    
    public static void displayOrderDetail(Integer index, OrderDetailDto ordersDetail) {
//        if (index == null) {
//
//        } else {
//            System.out.println("+\t" + index + ". Detail with id" + ordersDetail.getId());
//        }

        MenuDisplayUtil.displayMenuItem(index, ordersDetail.getMenuItem());
        System.out.println("+\tDetail id: " + ordersDetail.getId());
    }
}
