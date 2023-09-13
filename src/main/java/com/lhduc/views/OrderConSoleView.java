package com.lhduc.views;

import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.controllers.MenuItemController;
import com.lhduc.controllers.OrderController;
import com.lhduc.controllers.OrderDetailController;
import com.lhduc.models.dtos.OrderDetailDto;
import com.lhduc.models.dtos.OrderDto;
import com.lhduc.utils.OrderDisplayUtil;

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
        OrderDto createdOrderDto = orderController.create(new OrderDto());
        OrderDetailDto orderDetailDto = OrderDetailConsoleView.createOrderDetail(createdOrderDto.getId(), menuItemController, orderDetailController);

        createdOrderDto.addOrderDetail(orderDetailDto);

        OrderDisplayUtil.displayOrder(createdOrderDto);
    }

    private void updateOrder() {

    }

    private void deleteOrder() {

    }
}
