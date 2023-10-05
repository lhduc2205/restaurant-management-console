package com.lhduc.model.entity;

import com.lhduc.common.enums.PaymentStatus;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Comparable<Order> {
    private int id;

    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Order order) {
        this.id = order.id;
        this.createdAt = order.createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id &&
                paymentStatus == order.paymentStatus &&
                Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int compareTo(Order order) {
        return this.id - order.id;
    }
}
