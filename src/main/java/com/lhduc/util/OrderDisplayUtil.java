package com.lhduc.util;

import com.lhduc.common.enums.PaymentStatus;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;

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
        System.out.println("+ Payment status: " + order.getPaymentStatus().name());
        System.out.println("+ Placed date: " + DateUtil.formatDdMmYyyy(order.getPlacedAt()));
        System.out.println("+");
        displayOrderDetail(order.getOrderDetail());
    }

    public static void displayOrderDetail(List<OrderDetailDto> orderDetails) {
        if (orderDetails.isEmpty()) {
            System.out.println("+\tOrder details are empty.");
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
        System.out.println("+\t" + index + ". " + ordersDetail.getMenuItem().getName());
        System.out.println("+\tQuantity: " + ordersDetail.getQuantity());
        System.out.println("+\tPrice: " + ordersDetail.getPricePerUnit());
        System.out.println("+\tTotal price: " + ordersDetail.getTotalPrice());
    }

    public static void displayPaymentStatus() {
        for (int i = 0; i < PaymentStatus.values().length; i++) {
            System.out.println((i + 1) + ". " + PaymentStatus.values()[i]);
        }
    }
}
