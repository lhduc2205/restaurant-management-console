package views;

import common.enums.CrudOption;
import common.patterns.servicelocator.ServiceLocator;
import controllers.MenuItemController;
import controllers.OrderController;
import controllers.OrderDetailController;
import models.dtos.MenuItemDto;
import models.dtos.OrderDetailDto;
import models.dtos.OrderDto;
import utils.MenuDisplayUtil;
import utils.OrderDisplayUtil;
import utils.UserInputUtil;

public class OrderDetailConsoleView extends ConsoleViewTemplate {
    private final MenuItemController menuItemController;
    private final OrderDetailController orderDetailController;
    private final OrderController orderController;

    public OrderDetailConsoleView() {
        this.menuItemController = ServiceLocator.getService(MenuItemController.class.getName());
        this.orderDetailController = ServiceLocator.getService(OrderDetailController.class.getName());
        this.orderController = ServiceLocator.getService(OrderController.class.getName());
    }

    @Override
    protected String getOptionTitle() {
        return "Order Detail";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
        switch (option) {
            case SHOW: {
                this.showOrderDetail();
                break;
            }
            case CREATE: {
                this.createOrderDetail();
                break;
            }
            case UPDATE: {
                this.updateOderDetail();
                break;
            }
            case DELETE: {
                this.deleteOderDetail();
                break;
            }
        }
    }

    private void showOrderDetail() {
        OrderDisplayUtil.displayOrderDetail(orderDetailController.getAll());
    }

    private void createOrderDetail() {
        int orderId = UserInputUtil.enterInteger("Enter order id");
        OrderDto orderDto = orderController.getById(orderId);

        if (orderDto == null) {
            System.out.println("Does not exist order with id " + orderId);
            return;
        }

        OrderDetailDto orderDetailDto = OrderDetailConsoleView.createOrderDetail(orderId, menuItemController, orderDetailController);
        if (orderDetailDto != null) {
            OrderDisplayUtil.displayOrderDetail(0, orderDetailDto);
        }
    }

    static OrderDetailDto createOrderDetail(int orderId, MenuItemController menuItemController, OrderDetailController orderDetailController) {
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");

        MenuItemDto menuItemDto = menuItemController.getById(menuItemId);

        if (menuItemDto == null) {
            return null;
        }

        OrderDetailDto orderDetailDto = orderDetailController.create(new OrderDetailDto(orderId, menuItemDto));
        orderDetailDto.setMenuItem(menuItemDto);

        return orderDetailDto;
    }

    private void updateOderDetail() {

    }

    private void deleteOderDetail() {

    }
}
