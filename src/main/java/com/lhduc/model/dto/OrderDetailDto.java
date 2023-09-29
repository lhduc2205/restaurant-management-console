package com.lhduc.model.dto;

public class OrderDetailDto {
    private int id;
    private int menuItemId;
    private int orderId;
    private int quantity;
    private double pricePerUnit;
    private double totalPrice;
    private MenuItemDto menuItem = new MenuItemDto();

    public OrderDetailDto() {
    }

    public OrderDetailDto(int orderId, MenuItemDto menuItem, int quantity) {
        this.orderId = orderId;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.pricePerUnit = menuItem.getPrice();
    }

    public OrderDetailDto(int orderId, int menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    public OrderDetailDto(int orderId, int menuItemId, int quantity, MenuItemDto menuItem) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.pricePerUnit = menuItem.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public MenuItemDto getMenuItem() {
        return menuItem;
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

    public double getTotalPrice() {
        return pricePerUnit * quantity;
    }

    public void setMenuItem(MenuItemDto menuItem) {
        this.menuItem = menuItem;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }
}
