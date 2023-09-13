package com.lhduc.models.entities;

public class OrderDetail implements Comparable<OrderDetail> {
    private int id;
    private int menuItemId;
    private int orderId;

    public OrderDetail() {}

    public OrderDetail(int id, int menuItemId, int orderId) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.orderId = orderId;
    }

    public OrderDetail(int menuItemId, int orderId) {
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


    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderDetail orderDetail) {
            return this.id == orderDetail.id;
        }
        return false;
    }

    @Override
    public int compareTo(OrderDetail orderDetail) {
        return this.id - orderDetail.id;
    }
}
