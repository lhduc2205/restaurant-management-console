package com.lhduc.model.entity;

import com.lhduc.common.enums.PaymentStatus;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class Order implements Comparable<Order> {
    private int id;

    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    private LocalDateTime placedAt;

    public Order() {
    }

    public Order(Order order) {
        this.id = order.id;
        this.placedAt = order.placedAt;
        this.paymentStatus = order.paymentStatus;
    }

    public Order(int id, PaymentStatus paymentStatus) {
        this.id = id;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentStatus getOrderStatus() {
        return paymentStatus;
    }

    public void setOrderStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order order) {
            return this.id == order.id &&
                    this.paymentStatus == order.paymentStatus &&
                    this.placedAt == order.placedAt;
        }
        return false;
    }

    @Override
    public int compareTo(Order order) {
        return this.id - order.id;
    }
}
