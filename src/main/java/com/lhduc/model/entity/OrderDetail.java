package com.lhduc.model.entity;

import java.util.Objects;

public class OrderDetail {
    private int menuItemId;
    private int orderId;
    private int quantity;
    private double pricePerUnit;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetail orderDetail) {
        this.menuItemId = orderDetail.menuItemId;
        this.orderId = orderDetail.orderId;
        this.quantity = orderDetail.quantity;
        this.pricePerUnit = orderDetail.pricePerUnit;
    }

    public OrderDetail(int menuItemId, int orderId, int quantity, int pricePerUnit) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public OrderDetail(int menuItemId, int orderId) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuItemId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderDetail orderDetail) {
            return this.menuItemId == orderDetail.menuItemId &&
                    this.orderId == orderDetail.orderId &&
                    this.quantity == orderDetail.quantity &&
                    this.pricePerUnit == orderDetail.pricePerUnit;
        }
        return false;
    }
}
