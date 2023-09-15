package com.lhduc.views;

import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.controllers.MenuItemController;
import com.lhduc.controllers.OrderController;
import com.lhduc.controllers.OrderDetailController;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.models.dtos.OrderDetailDto;
import com.lhduc.models.dtos.OrderDto;
import com.lhduc.utils.OrderDisplayUtil;
import com.lhduc.utils.UserInputUtil;

public class OrderConSoleView extends ConsoleViewTemplate {
    private final OrderController orderController;
    private final OrderDetailController orderDetailController;
    private final MenuItemController menuItemController;

    public OrderConSoleView() {
        this.orderController = ServiceLocator.getService(OrderController.class.getName());
        this.orderDetailController = ServiceLocator.getService(OrderDetailController.class.getName());
        this.menuItemController = ServiceLocator.getService(MenuItemController.class.getName());
    }

    @Override
    protected String getOptionTitle() {
        return "Order";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
        switch (option) {
            case SHOW: {
                this.showOrder();
                break;
            }
            case CREATE: {
                this.createOrder();
                break;
            }
            case UPDATE: {
                this.updateOrder();
                break;
            }
            case DELETE: {
                this.deleteOrder();
                break;
            }
        }
    }

    private void showOrder() {
        OrderDisplayUtil.displayOrder(this.orderController.getAll());
    }

    private void createOrder() {
        try {
            OrderDto createdOrder = orderController.create(new OrderDto());

            int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
            MenuItemDto menuItem = menuItemController.getById(menuItemId);

            OrderDetailDto orderDetail = orderDetailController.create(new OrderDetailDto(createdOrder.getId(), menuItem));
            orderDetail.setMenuItem(menuItem);

            createdOrder.addOrderDetail(orderDetail);
            OrderDisplayUtil.displayOrder(createdOrder);
        } catch (ResourceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOrder() {
    }

    private void deleteOrder() {
        int orderId = UserInputUtil.enterInteger("Enter order id");

        try {
            orderController.deleteById(orderId);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
