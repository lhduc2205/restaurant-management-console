package com.lhduc.views;

import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.controllers.MenuItemController;
import com.lhduc.controllers.OrderController;
import com.lhduc.controllers.OrderDetailController;
import com.lhduc.exceptions.ApplicationRuntimeException;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.models.dtos.OrderDetailDto;
import com.lhduc.models.dtos.OrderDto;
import com.lhduc.utils.OrderDisplayUtil;
import com.lhduc.utils.UserInputUtil;

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
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");

        try {
            MenuItemDto menuItemDto = menuItemController.getById(menuItemId);
            OrderDetailDto orderDetailDto = orderDetailController.create(new OrderDetailDto(orderId, menuItemDto));
            orderDetailDto.setMenuItem(menuItemDto);

            OrderDisplayUtil.displayOrderDetail(0, orderDetailDto);
        } catch (ResourceAlreadyExistsException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOderDetail() {
        int orderDetailId = UserInputUtil.enterInteger("Enter order detail id");
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");

        try {
            OrderDetailDto existedOrderDetail = orderDetailController.getById(orderDetailId);
            MenuItemDto existedMenuItem = menuItemController.getById(orderDetailId);

            existedOrderDetail.setMenuItemId(menuItemId);
            existedOrderDetail.setMenuItem(existedMenuItem);

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
