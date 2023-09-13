package views;

import common.enums.CrudOption;
import common.patterns.servicelocator.ServiceLocator;
import controllers.MenuItemController;
import controllers.OrderController;
import controllers.OrderDetailController;
import models.dtos.OrderDetailDto;
import models.dtos.OrderDto;
import utils.MenuDisplayUtil;
import utils.OrderDisplayUtil;

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
