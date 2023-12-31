package com.lhduc.view;

import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.controller.MenuItemController;
import com.lhduc.controller.OrderController;
import com.lhduc.controller.OrderDetailController;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.ResourceAlreadyExistsException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.util.OrderDisplayUtil;
import com.lhduc.util.UserInputUtil;

public class OrderDetailConsoleView extends ConsoleViewTemplate {
    private final MenuItemController menuItemController;
    private final OrderDetailController orderDetailController;
    private final OrderController orderController;

    public OrderDetailConsoleView() {
        this.menuItemController = ServiceLocator.getService(MenuItemController.class);
        this.orderDetailController = ServiceLocator.getService(OrderDetailController.class);
        this.orderController = ServiceLocator.getService(OrderController.class);
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
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        int quantity = UserInputUtil.enterInteger("Enter quantity");

        try {
            orderController.getById(orderId);
            MenuItemDto menuItemDto = menuItemController.getById(menuItemId);
            OrderDetailDto orderDetailDto = orderDetailController.create(new OrderDetailDto(orderId, menuItemDto, quantity));
            orderDetailDto.setMenuItem(menuItemDto);

            OrderDisplayUtil.displayOrderDetail(0, orderDetailDto);
        } catch (ResourceAlreadyExistsException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOderDetail() {
        int orderDetailId = UserInputUtil.enterInteger("Enter order detail id");
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        int quantity = UserInputUtil.enterInteger("Enter quantity");

        try {
            OrderDetailDto existedOrderDetail = orderDetailController.getById(orderDetailId);
            MenuItemDto existedMenuItem = menuItemController.getById(orderDetailId);

            existedOrderDetail.setMenuItemId(menuItemId);
            existedOrderDetail.setMenuItem(existedMenuItem);
            existedOrderDetail.setQuantity(quantity);

            orderDetailController.update(existedOrderDetail);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOderDetail() {
        int orderDetailId = UserInputUtil.enterInteger("Enter order detail id");

        try {
            orderDetailController.deleteById(orderDetailId);
        } catch (ApplicationRuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
