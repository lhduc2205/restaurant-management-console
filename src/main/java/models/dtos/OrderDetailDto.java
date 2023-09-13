package models.dtos;

public class OrderDetailDto {
    private int id;
    private int menuItemId;
    private int orderId;
    private MenuItemDto menuItem = new MenuItemDto();

    public OrderDetailDto() {}

    public OrderDetailDto(int orderId, MenuItemDto menuItem) {
        this.orderId = orderId;
        this.menuItem = menuItem;
    }

    public OrderDetailDto(int orderId, int menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    public OrderDetailDto(int orderId, int menuItemId, MenuItemDto menuItem) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.menuItem = menuItem;
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
