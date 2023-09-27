package com.lhduc.model.dto;

import com.lhduc.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private int id;
    private double totalPrice;
    private int quantity;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private LocalDateTime placedAt;
    private List<OrderDetailDto> orderDetail = new ArrayList<>();

    public OrderDto() {
    }

    public OrderDto(double totalPrice, int quantity) {
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        this.totalPrice = orderDetail.stream().reduce(0d, (total, e) -> total + e.getTotalPrice(), Double::sum);
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        this.quantity = orderDetail.size();
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public List<OrderDetailDto> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDto> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void addOrderDetail(OrderDetailDto orderDetailDto) {
        this.orderDetail.add(orderDetailDto);
    }
}
