package com.lhduc.model.dto;

import com.lhduc.common.enums.PaymentStatus;
import com.lhduc.common.filtered.Filterable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto implements Filterable, Comparable<OrderDto> {
    private int id;
    private double totalPrice;
    private int quantity;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private LocalDateTime createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public int compareTo(OrderDto that) {
        return this.id - that.id;
    }

    @Override
    public String[] getFilterableFieldNames() {
        return new String[] {"id", "totalPrice", "quantity", "paymentStatus", "createdAt"};
    }
}
