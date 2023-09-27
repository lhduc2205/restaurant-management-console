package com.lhduc.model.entity;

public class OrderDetail implements Comparable<OrderDetail> {
    private int id;
    private int menuItemId;
    private int orderId;
    private int quantity;
    private double pricePerUnit;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetail orderDetail) {
        this.id = orderDetail.id;
        this.menuItemId = orderDetail.menuItemId;
        this.orderId = orderDetail.orderId;
        this.quantity = orderDetail.quantity;
        this.pricePerUnit = orderDetail.pricePerUnit;
    }

    public OrderDetail(int id, int menuItemId, int orderId, int quantity, int pricePerUnit) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public OrderDetail(int id, int menuItemId, int orderId) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderDetail orderDetail) {
            return this.id == orderDetail.id &&
                    this.menuItemId == orderDetail.menuItemId &&
                    this.orderId == orderDetail.orderId &&
                    this.quantity == orderDetail.quantity &&
                    this.pricePerUnit == orderDetail.pricePerUnit;
        }
        return false;
    }

    @Override
    public int compareTo(OrderDetail orderDetail) {
        return this.id - orderDetail.id;
    }
}
