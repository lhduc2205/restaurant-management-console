package com.lhduc.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private int id;
    private double totalPrice;
    private int quantity;
    private List<OrderDetailDto> orderDetail = new ArrayList<>();

    public OrderDto() {}

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
        this.totalPrice = orderDetail.stream().reduce(0d, (total, e) -> total + e.getMenuItem().getPrice(), Double::sum);
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
